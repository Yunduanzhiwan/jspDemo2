/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yefeng.experiment21.model;

public class ReplyBean {
    private String Id;
    private String TopicId;
    private String userName;
    private int userId;
    private String replyContent;
    private String replyDate;


    private String userAvatar;


    @Override
    public String toString() {
        return "ReplyBean{" +
                "Id='" + Id + '\'' +
                ", TopicId='" + TopicId + '\'' +
                ", userName='" + userName + '\'' +
                ", userId=" + userId +
                ", replyContent='" + replyContent + '\'' +
                ", replyDate='" + replyDate + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                '}';
    }


    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the Id
     */
    public String getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     * @return the TopicId
     */
    public String getTopicId() {
        return TopicId;
    }

    /**
     * @param TopicId the TopicId to set
     */
    public void setTopicId(String TopicId) {
        this.TopicId = TopicId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the replyContent
     */
    public String getReplyContent() {
        return replyContent;
    }

    /**
     * @param replyContent the replyContent to set
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    /**
     * @return the replyDate
     */
    public String getReplyDate() {
        return replyDate;
    }

    /**
     * @param replyDate the replyDate to set
     */
    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

   
}
