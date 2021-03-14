package com.imooc.ad.entity;

import com.imooc.ad.constant.CommonStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Created by lwj32 on 2021/2/21.
 */
@Entity
@Table(name = "ad_plan", schema = "imooc_ad_data", catalog = "")
public class AdPlan {
    private long id;
    private long userId;
    private String planName;
    private int planStatus;
    private Date startDate;
    private Date endDate;
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
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "plan_name")
    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    @Basic
    @Column(name = "plan_status")
    public int getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(int planStatus) {
        this.planStatus = planStatus;
    }

    @Basic
    @Column(name = "start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        AdPlan adPlan = (AdPlan) o;
        return id == adPlan.id &&
                userId == adPlan.userId &&
                planStatus == adPlan.planStatus &&
                Objects.equals(planName, adPlan.planName) &&
                Objects.equals(startDate, adPlan.startDate) &&
                Objects.equals(endDate, adPlan.endDate) &&
                Objects.equals(createTime, adPlan.createTime) &&
                Objects.equals(updateTime, adPlan.updateTime);
    }
    public AdPlan(Long userId, String planName,
                  java.util.Date startDate, java.util.Date endDate) {

        this.userId = userId;
        this.planName = planName;
        this.planStatus = CommonStatus.VALID.getStatus();
        this.startDate = startDate;
        this.endDate = endDate;
        this.createTime = new java.util.Date();
        this.updateTime = this.createTime;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, userId, planName, planStatus, startDate, endDate, createTime, updateTime);
    }
}
