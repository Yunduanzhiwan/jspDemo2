package com.yefeng.experiment21.model;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName FollowBean.java
 * @Description TODO 关注
 * @createTime 2022年06月07日 10:18:00
 */
public class FollowBean {
    private int Id;
    /**
     * 用户名
     */
    private String username;
    private int userId;
    /**
     * 关注用户名
     */
    private String followUsername;
    private int followUserId;
    /**
     * 关注日期
     */
    private String followDate;

    @Override
    public String toString() {
        return "FollowBean{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                ", userId=" + userId +
                ", followUsername='" + followUsername + '\'' +
                ", followUserId=" + followUserId +
                ", followDate='" + followDate + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFollowUserId() {
        return followUserId;
    }

    public void setFollowUserId(int followUserId) {
        this.followUserId = followUserId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFollowUsername() {
        return followUsername;
    }

    public void setFollowUsername(String followUsername) {
        this.followUsername = followUsername;
    }

    public String getFollowDate() {
        return followDate;
    }

    public void setFollowDate(String followDate) {
        this.followDate = followDate;
    }
}
