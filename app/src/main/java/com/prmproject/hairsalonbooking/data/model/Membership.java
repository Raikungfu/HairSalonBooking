package com.prmproject.hairsalonbooking.data.model;

import java.util.Set;
import java.util.HashSet;

public class Membership {

    private int membershipId;
    private Integer level;
    private String detail;
    private Integer status;
    private String createDate;
    private String createBy;
    private String updateDate;
    private String updateBy;

    private Set<UserMembership> userMemberships = new HashSet<>();

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Set<UserMembership> getUserMemberships() {
        return userMemberships;
    }

    public void setUserMemberships(Set<UserMembership> userMemberships) {
        this.userMemberships = userMemberships;
    }
}
