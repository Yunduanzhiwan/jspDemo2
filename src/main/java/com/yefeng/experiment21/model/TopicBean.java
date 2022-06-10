/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yefeng.experiment21.model;

import java.math.BigInteger;

/**
 *
 * @author 97549
 */
public class TopicBean {
    private int Id;
    private String username;
    private String avatar;
    private String title;
    private String content;
    private String topicdate;
    /**
     * 点赞数
     */
    private int likeCount;

    private int replycount;
    /**
     * 子论坛名称
     */
    private String forumName;
    /**
     * 论坛id
     */
    private int forumId;
    /**
     * 发布人id
     */
    private int postId;


    @Override
    public String toString() {
        return "TopicBean{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", topicdate='" + topicdate + '\'' +
                ", likeCount=" + likeCount +
                ", replycount=" + replycount +
                ", forumName='" + forumName + '\'' +
                ", forumId=" + forumId +
                ", postId=" + postId +
                '}';
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the topicdate
     */
    public String getTopicdate() {
        return topicdate;
    }

    /**
     * @param topicdate the topicdate to set
     */
    public void setTopicdate(String topicdate) {
        this.topicdate = topicdate;
    }

    /**
     * @return the replycount
     */
    public int getReplycount() {
        return replycount;
    }

    /**
     * @param replycount the replycount to set
     */
    public void setReplycount(int replycount) {
        this.replycount = replycount;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId=postId;
    }
}
