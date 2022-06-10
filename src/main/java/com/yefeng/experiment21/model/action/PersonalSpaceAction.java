package com.yefeng.experiment21.model.action;

import com.yefeng.experiment21.model.ConnDB;
import com.yefeng.experiment21.model.PersonalSpace;
import com.yefeng.experiment21.model.TopicBean;
import com.yefeng.experiment21.model.UserBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName PersonalSpaceAction.java
 * @Description TODO 个人空间
 * @createTime 2022年06月07日 11:13:00
 */
public class PersonalSpaceAction {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    //    获取个人空间信息
    public PersonalSpace getPersonalSpace(int userId) {
        PersonalSpace personalSpace = new PersonalSpace();

        UserBean userInfo = this.getUserInfo(userId);
        personalSpace.setUser(userInfo);

        List<TopicBean> followTopicList = this.getFollowTopicList(userId);
        personalSpace.setFollowTopicList(followTopicList);

        List<TopicBean> myTopicList = this.getMyTopicList(userId);
        personalSpace.setMyTopicList(myTopicList);

        List<TopicBean> myReplyTopicList = this.getMyReplyTopicList(userId);
        personalSpace.setMyReplyTopicList(myReplyTopicList);

        return personalSpace;
    }

    /**
     * @param userId 用户id
     * @return {@link List }<{@link TopicBean }>
     * @throws
     * @title getMyReplyTopicList
     * @author yefeng
     * @description TODO 我回答的主题列表
     * @updateTime 2022/06/09
     */
    private List<TopicBean> getMyReplyTopicList(int userId) {
        System.out.println("userId"+userId);
        List<TopicBean> myReplyTopicList = new ArrayList<>();
        String sql = "select t.id,t.title,t.content,topicdate,replycount,t.likeCount,\n" +
                "       f.name as forumName,username as postname,forumId,postId\n" +
                "from topic t join user_info ui on t.postId= ui.id\n" +
                "join forum f on f.id=t.forumId\n" +
                "where t.id in (select distinct topicId from reply where userId=?)";
        try {
            conn = ConnDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                TopicBean topicBean = new TopicBean();
                topicBean.setId(rs.getInt("id"));
                topicBean.setTitle(rs.getString("title"));
                topicBean.setContent(rs.getString("content"));
                topicBean.setLikeCount(rs.getInt("likeCount"));
                topicBean.setReplycount(rs.getInt("replycount"));
                topicBean.setPostId(rs.getInt("postId"));
                topicBean.setUsername(rs.getString("postname"));

                topicBean.setForumId(rs.getInt("forumId"));
                topicBean.setForumName(rs.getString("forumName"));
                topicBean.setTopicdate(rs.getString("topicdate"));

                myReplyTopicList.add(topicBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnDB.close(rs, ps, conn);
        }
        return myReplyTopicList;
    }

    //    获取关注用户发过的帖子 不分组
    private List<TopicBean> getFollowTopicList(int userId) {
        List<TopicBean> topicList = new ArrayList<>();

        try {
            String sql = "select t.id,title,content,postname,topicdate,replycount,t.likeCount,forumId,f.name as forumName\n" +
                    ",f.name as forumName,postId from topic t join forum f on t.forumId=f.id\n" +
                    "join follow on followUId= t.postId\n" +
                    "where uId = ?";
            conn=ConnDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                TopicBean topicBean = new TopicBean();
                topicBean.setId(rs.getInt("id"));
                topicBean.setTitle(rs.getString("title"));
                topicBean.setContent(rs.getString("content"));
                topicBean.setLikeCount(rs.getInt("likeCount"));
                topicBean.setReplycount(rs.getInt("replycount"));
                topicBean.setPostId(rs.getInt("postId"));
                topicBean.setUsername(rs.getString("postname"));

                topicBean.setForumId(rs.getInt("forumId"));
                topicBean.setForumName(rs.getString("forumName"));
                topicBean.setTopicdate(rs.getString("topicdate"));
                topicList.add(topicBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnDB.close(rs, ps, conn);
        }

        return topicList;
    }

    //    获取我主题列表
    private List<TopicBean> getMyTopicList(int userId) {
        ArrayList<TopicBean> topicBeans = new ArrayList<>();

        try {
            String sql = "select t.id,title,content,ui.username as postname,topicdate,replycount,t.likeCount,forumId,f.name as forumName,postId from topic t left join forum f on f.id=t.forumId join user_info ui on t.postId=ui.id where postId = ?";
            conn=ConnDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                TopicBean topicBean = new TopicBean();
                topicBean.setId(rs.getInt("id"));
                topicBean.setTitle(rs.getString("title"));
                topicBean.setContent(rs.getString("content"));
                topicBean.setLikeCount(rs.getInt("likeCount"));
                topicBean.setReplycount(rs.getInt("replycount"));
                topicBean.setPostId(rs.getInt("postId"));
                topicBean.setUsername(rs.getString("postname"));

                topicBean.setForumId(rs.getInt("forumId"));
                topicBean.setForumName(rs.getString("forumName"));
                topicBean.setTopicdate(rs.getString("topicdate"));
                topicBeans.add(topicBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnDB.close(rs, ps, conn);
        }
        return topicBeans;
    }

    //    获取我回帖列表

    //获取用户信息
    private UserBean getUserInfo(int userId) {
        UserBeanAction userBeanAction = new UserBeanAction();

        return userBeanAction.getUserInfo(userId);
    }


}
