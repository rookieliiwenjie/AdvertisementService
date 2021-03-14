package com.imooc.ad.mysql.listener;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.EventType;
import com.github.shyiko.mysql.binlog.event.TableMapEventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;
import com.imooc.ad.mysql.TemplateHolder;
import com.imooc.ad.mysql.dto.BinlogRowData;
import com.imooc.ad.mysql.dto.TableTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Qinyi.
 */
@Slf4j
@Component
public class AggregationListener implements BinaryLogClient.EventListener {

    private String dbName;
    private String tableName;

    private Map<String, Ilistener> listenerMap = new HashMap<>();

    private final TemplateHolder templateHolder;

    @Autowired
    public AggregationListener(TemplateHolder templateHolder) {
        this.templateHolder = templateHolder;
    }

    private String genKey(String dbName, String tableName) {
        return dbName + ":" + tableName;
    }

    public void register(String _dbName, String _tableName,
                         Ilistener ilistener) {
        log.info("register : {}-{}", _dbName, _tableName);
        this.listenerMap.put(genKey(_dbName, _tableName), ilistener);
    }

    @Override
    public void onEvent(Event event) {

        EventType type = event.getHeader().getEventType();
        /**
         * binlog.000015	1999	Table_map	1	2088	table_id: 96 (imooc_ad_data.ad_creative)
         * binlog.000015	2088	Write_rows	1	2182	table_id: 96 flags: STMT_END_F
         * binlog.000015	2182	Xid	1	2213	COMMIT /* xid=4027 */
       /*  *binlog .000015 2213 Anonymous_Gtid 1 2292 SET @ @SESSION.GTID_NEXT='ANONYMOUS'
                * binlog .000015 2292 Query 1 2385 BEGIN
                * binlog .000015 2385 Table_map 1 2474 table_id:
        96 (imooc_ad_data.ad_creative)
                * binlog .000015 2474 Update_rows 1 2628 table_id:
        96 flags:
        STMT_END_F
                * binlog .000015 2628 Xid 1 2659 COMMIT *//* xid=4033 *//*
                * binlog .000015 2659 Anonymous_Gtid 1 2738 SET @ @SESSION.GTID_NEXT='ANONYMOUS'
                * binlog .000015 2738 Query 1 2831 BEGIN
                * binlog .000015 2831 Table_map 1 2920 table_id:
        96 (imooc_ad_data.ad_creative)
                * binlog .000015 2920 Update_rows 1 3074 table_id:
        96 flags:
        STMT_END_F
                * binlog .000015 3074 Xid 1 3105 COMMIT *//* xid=4035 *//*
                * binlog .000015 3105 Anonymous_Gtid 1 3184 SET @ @SESSION.GTID_NEXT='ANONYMOUS'
                * binlog .000015 3184 Query 1 3276 BEGIN
                * binlog .000015 3276 Table_map 1 3365 table_id:
        96 (imooc_ad_data.ad_creative)
                * binlog .000015 3365 Write_rows 1 3459 table_id:
        96 flags:
        STMT_END_F
                * binlog .000015 3459 Xid 1 3490 COMMIT
                * binlog .000015 3490 Anonymous_Gtid 1 3569 SET @ @SESSION.GTID_NEXT='ANONYMOUS'
                * binlog .000015 3569 Query 1 3662 BEGIN
                * binlog .000015 3662 Table_map 1 3751 table_id:
        96 (imooc_ad_data.ad_creative)
                * binlog .000015 3751 Update_rows 1 3905 table_id:
        96 flags:
        STMT_END_F
                * binlog .000015 3905 Xid 1 3936 COMMIT */

        log.debug("event type: {}", type);

        if (type == EventType.TABLE_MAP) {
            TableMapEventData data = event.getData();
            this.tableName = data.getTable();
            this.dbName = data.getDatabase();
            return;
        }

        if (type != EventType.EXT_UPDATE_ROWS
                && type != EventType.EXT_WRITE_ROWS
                && type != EventType.EXT_DELETE_ROWS) {
            return;
        }

        // 表名和库名是否已经完成填充
        if (StringUtils.isEmpty(dbName) || StringUtils.isEmpty(tableName)) {
            log.error("no meta data event");
            return;
        }

        // 找出对应表有兴趣的监听器
        String key = genKey(this.dbName, this.tableName);
        Ilistener listener = this.listenerMap.get(key);
        if (null == listener) {
            log.debug("skip {}", key);
            return;
        }

        log.info("trigger event: {}", type.name());

        try {

            BinlogRowData rowData = buildRowData(event.getData());
            if (rowData == null) {
                return;
            }

            rowData.setEventType(type);
            listener.onEvent(rowData);

        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
        } finally {
            this.dbName = "";
            this.tableName = "";
        }
    }

    private List<Serializable[]> getAfterValues(EventData eventData) {

        if (eventData instanceof WriteRowsEventData) {
            return ((WriteRowsEventData) eventData).getRows();
        }

        if (eventData instanceof UpdateRowsEventData) {
            return ((UpdateRowsEventData) eventData).getRows().stream()
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
        }

        if (eventData instanceof DeleteRowsEventData) {
            return ((DeleteRowsEventData) eventData).getRows();
        }

        return Collections.emptyList();
    }

    private BinlogRowData buildRowData(EventData eventData) {

        TableTemplate table = templateHolder.getTable(tableName);

        if (null == table) {
            log.warn("table {} not found", tableName);
            return null;
        }

        List<Map<String, String>> afterMapList = new ArrayList<>();

        for (Serializable[] after : getAfterValues(eventData)) {

            Map<String, String> afterMap = new HashMap<>();

            int colLen = after.length;

            for (int ix = 0; ix < colLen; ++ix) {

                // 取出当前位置对应的列名
                String colName = table.getPosMap().get(ix);

                // 如果没有则说明不关心这个列
                if (null == colName) {
                    log.debug("ignore position: {}", ix);
                    continue;
                }

                String colValue = after[ix].toString();
                afterMap.put(colName, colValue);
            }

            afterMapList.add(afterMap);
        }

        BinlogRowData rowData = new BinlogRowData();
        rowData.setAfter(afterMapList);
        rowData.setTable(table);

        return rowData;
    }
}
