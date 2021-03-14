package com.imooc.ad.entity;

import com.imooc.ad.constant.CommonStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Created by lwj32 on 2021/2/21.
 */
@Entity
@Table(name = "ad_unit", schema = "imooc_ad_data", catalog = "")
public class AdUnit {
    private long id;
    private long planId;
    private String unitName;
    private Integer unitStatus;
    private int positionType;
    private long budget;
    private Date createTime;
    private Date updateTime;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "plan_id")
    public long getPlanId() {
        return planId;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    @Basic
    @Column(name = "unit_name")
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Basic
    @Column(name = "unit_status")
    public Integer getUnitStatus() {
        return unitStatus;
    }

    public void setUnitStatus(Integer unitStatus) {
        this.unitStatus = unitStatus;
    }

    @Basic
    @Column(name = "position_type")
    public int getPositionType() {
        return positionType;
    }

    public void setPositionType(int positionType) {
        this.positionType = positionType;
    }

    @Basic
    @Column(name = "budget")
    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
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
        AdUnit adUnit = (AdUnit) o;
        return id == adUnit.id &&
                planId == adUnit.planId &&
                unitStatus == adUnit.unitStatus &&
                positionType == adUnit.positionType &&
                budget == adUnit.budget &&
                Objects.equals(unitName, adUnit.unitName) &&
                Objects.equals(createTime, adUnit.createTime) &&
                Objects.equals(updateTime, adUnit.updateTime);
    }

    public AdUnit(Long planId, String unitName,
                  Integer positionType, Long budget) {
        this.planId = planId;
        this.unitName = unitName;
        this.unitStatus = CommonStatus.VALID.getStatus();
        this.positionType = positionType;
        this.budget = budget;
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, planId, unitName, unitStatus, positionType, budget, createTime, updateTime);
    }
}
