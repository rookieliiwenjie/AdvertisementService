package com.imooc.ad.entity;

import com.imooc.ad.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import  java.util.Date;
import java.util.Objects;

/**
 * Created by lwj32 on 2021/2/21.
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ad_user", schema = "imooc_ad_data", catalog = "")
public class AdUser {
    private long id;
    private String username;
    private String token;
    private int userStatus;
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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "user_status")
    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
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
        AdUser adUser = (AdUser) o;
        return id == adUser.id &&
                userStatus == adUser.userStatus &&
                Objects.equals(username, adUser.username) &&
                Objects.equals(token, adUser.token) &&
                Objects.equals(createTime, adUser.createTime) &&
                Objects.equals(updateTime, adUser.updateTime);
    }
    public AdUser(String username, String token) {

        this.username = username;
        this.token = token;
        this.userStatus = CommonStatus.VALID.getStatus();
        this.createTime = new java.util.Date();
        this.updateTime = this.createTime;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, username, token, userStatus, createTime, updateTime);
    }
}
