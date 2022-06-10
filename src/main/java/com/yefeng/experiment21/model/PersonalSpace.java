package com.yefeng.experiment21.model;

import java.util.List;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName PersonalSpace.java
 * @Description TODO 个人空间
 * @createTime 2022年06月07日 11:06:00
 */
public class PersonalSpace {
    private UserBean user;


    /**
     * 关注用户发过的帖子 不分组
     */
    private List<TopicBean> followTopicList;


    /**
     * 我主题列表
     */
    private List<TopicBean> myTopicList;

    /**
     * 我回答的主题列表
     */
    private List<TopicBean> myReplyTopicList;

    /**
     * 我获得的点赞数
     */
    private int likeCount;



    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<TopicBean> getFollowTopicList() {
        return followTopicList;
    }

    public void setFollowTopicList(List<TopicBean> followTopicList) {
        this.followTopicList = followTopicList;
    }

    public List<TopicBean> getMyTopicList() {
        return myTopicList;
    }

    public void setMyTopicList(List<TopicBean> myTopicList) {
        this.myTopicList = myTopicList;
    }

    public List<TopicBean> getMyReplyTopicList() {
        return myReplyTopicList;
    }

    public void setMyReplyTopicList(List<TopicBean> myReplyTopicList) {
        this.myReplyTopicList = myReplyTopicList;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    @Override
    public String toString() {
        return "PersonalSpace{" +
                "user=" + user +
                ", followTopicList=" + followTopicList +
                ", myTopicList=" + myTopicList +
                ", myReplyTopicList=" + myReplyTopicList +
                ", likeCount=" + likeCount +
                '}';
    }
}
