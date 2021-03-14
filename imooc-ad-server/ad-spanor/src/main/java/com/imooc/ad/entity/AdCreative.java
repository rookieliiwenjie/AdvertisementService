package com.imooc.ad.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Created by lwj32 on 2021/2/21.
 */
@Entity
@Table(name = "ad_creative", schema = "imooc_ad_data", catalog = "")
public class AdCreative {
    private long id;
    private String name;
    private int type;
    private int materialType;
    private int height;
    private int width;
    private long size;
    private int duration;
    private int auditStatus;
    private long userId;
    private String url;
    private Date createTime;
    private Date updateTime;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "material_type")
    public int getMaterialType() {
        return materialType;
    }

    public void setMaterialType(int materialType) {
        this.materialType = materialType;
    }

    @Basic
    @Column(name = "height")
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Basic
    @Column(name = "width")
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Basic
    @Column(name = "size")
    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Basic
    @Column(name = "duration")
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "audit_status")
    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }

    @Basic
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdCreative that = (AdCreative) o;
        return id == that.id &&
                type == that.type &&
                materialType == that.materialType &&
                height == that.height &&
                width == that.width &&
                size == that.size &&
                duration == that.duration &&
                auditStatus == that.auditStatus &&
                userId == that.userId &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, materialType, height, width, size, duration, auditStatus, userId, url, createTime, updateTime);
    }
}
