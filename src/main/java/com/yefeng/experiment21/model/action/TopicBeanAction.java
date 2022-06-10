/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yefeng.experiment21.model.action;

import com.yefeng.experiment21.model.ConnDB;
import com.yefeng.experiment21.model.TopicBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author 97549
 */
public class TopicBeanAction {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    //    添加文章到指定的主题
    public boolean addTopicToForum(String topicName, String topicContent, String userName, int postId, int forumId) {
        boolean flag = false;
        try {
            conn = ConnDB.getConnection();
            String sql = "INSERT INTO topic(title, content, postname,postId, forumId) VALUES(?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, topicName);
            ps.setString(2, topicContent);
            ps.setString(3, userName);
            ps.setInt(4, postId);
            ps.setInt(5, forumId);

            ps.executeUpdate();
            System.out.println("添加文章成功");
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(TopicBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnDB.close(rs, ps, conn);
        }
        return flag;
    }

    //添加文章
    public boolean addTopic(String username, String title, String content) {
        boolean flag = false;
        try {

            String sql = "insert into topic(postname,title,content) values(?,?,?)";
            conn = ConnDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, title);
            ps.setString(3, content);
            ps.executeUpdate();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnDB.close(rs, ps, conn);
        }
        return flag;
    }

    /**
     * @param pageSize    页面大小
     * @param currentPage 当前页面
     * @return {@link ArrayList }
     * @title getTopicByPage
     * @author yefeng
     * @description TODO 根据页面大小和当前页面获取帖子列表
     */
    public ArrayList getTopicByPage(int pageSize, int currentPage) {
        ArrayList al = new ArrayList();
        try {
            String sql = "select t.id,title,content,topicdate,replycount,t.likeCount,f.name as forumName,username as postname,forumId,postId from topic t join forum f on t.forumId=f.id\n" +
                    "join user_info u on t.postId=u.id limit ?,?";
            System.out.println(sql);
            conn = ConnDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pageSize * (currentPage - 1));
            ps.setInt(2, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                TopicBean tb = new TopicBean();
                tb.setId(rs.getInt("id"));
                tb.setUsername(rs.getString("postname"));
                tb.setTitle(rs.getString("title"));
                tb.setContent(rs.getString("content"));
                tb.setReplycount(rs.getInt("replycount"));
                tb.setTopicdate(rs.getString("topicdate"));
                tb.setLikeCount(rs.getInt("likeCount"));

                tb.setForumName(rs.getString("forumName"));
                tb.setForumId(rs.getInt("forumId"));
                tb.setPostId(rs.getInt("postId"));


                al.add(tb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnDB.close(rs, ps, conn);
        }
        return al;
    }

    public ArrayList getTopicByPage(int pageSize, int currentPage, String searchKey) {
        System.out.println(searchKey);
        ArrayList al = new ArrayList();
        try {
            String sql = "select t.id,title,content,topicdate,replycount,t.likeCount,f.name as forumName,username as postname,forumId,postId from topic t join forum f on t.forumId=f.id\n" +
                    "join user_info u on t.postId=u.id  where (title like ? or content like ? or postname like ?) limit ?,?";
            System.out.println(sql);
            conn = ConnDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchKey + "%");
            ps.setString(2, "%" + searchKey + "%");
            ps.setString(3, "%" + searchKey + "%");
            ps.setInt(4, pageSize * (currentPage - 1));
            ps.setInt(5, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                TopicBean tb = new TopicBean();
                tb.setId(rs.getInt("id"));
                tb.setUsername(rs.getString("postname"));
                tb.setTitle(rs.getString("title"));
                tb.setContent(rs.getString("content"));
                tb.setReplycount(rs.getInt("replycount"));
                tb.setTopicdate(rs.getString("topicdate"));
                tb.setLikeCount(rs.getInt("likeCount"));

                tb.setForumName(rs.getString("forumName"));
                tb.setForumId(rs.getInt("forumId"));
                tb.setPostId(rs.getInt("postId"));
                al.add(tb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnDB.close(rs, ps, conn);
        }
        return al;
    }

    //点赞
    public int likeTopic(int userId, int topicId) {
        int flag = -1;
        try {

//            //查询是否已经点赞
//            String sql = "select * from topic_like where uid=? and tid=?";
//            conn = ConnDB.getConnection();
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, userId);
//            ps.setInt(2, topicId);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                flag = 0;
//                close();
//                return 0;
//            }
            //没有点赞，添加点赞

            String   sql = "insert into topic_like(uid,tid) values(?,?)";
            conn = ConnDB.getConnection();

            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, topicId);
            ps.executeUpdate();


            sql = "update topic set likeCount=likeCount+1,hot=hot+1 where id=?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, topicId);
            ps.executeUpdate();


            sql = "update user_info u set u.u_likeCount=u.u_likeCount+1\n" +
                    "where id=(select postId from topic where id=?)";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, topicId);
            ps.executeUpdate();


            conn.commit();

            flag = 1;

        } catch (SQLException ex) {

            try {
                conn.rollback();
                flag = -1;
            } catch (SQLException ex1) {
                Logger.getLogger(TopicBeanAction.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(UserBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnDB.close(rs, ps, conn);
        }
        return flag;
    }


    /**
     * @param id id
     * @return {@link TopicBean }
     * @throws
     * @title getTopicById
     * @author yefeng
     * @description TODO 根据id获取帖子
     */
    public TopicBean getTopicById(int id) {
        TopicBean tb = new TopicBean();
        try {
            String sql = "select t.id,title,content,replycount,topicdate,t.likeCount,forumId,postId,f.name as forumName,ui.username as postname,ui.avatar from topic t join user_info ui on t.postId=ui.id join forum f on f.id=t.forumId where t.id=?";
            conn = ConnDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                tb.setId(rs.getInt("id"));
                tb.setUsername(rs.getString("postname"));
                tb.setTitle(rs.getString("title"));
                tb.setContent(rs.getString("content"));
                tb.setReplycount(rs.getInt("replycount"));
                tb.setTopicdate(rs.getString("topicdate"));

                tb.setLikeCount(rs.getInt("likeCount"));
                tb.setForumId(rs.getInt("forumId"));
                tb.setForumName(rs.getString("forumName"));
                tb.setPostId(rs.getInt("postId"));

                tb.setAvatar(rs.getString("avatar"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnDB.close(rs, ps, conn);
        }
        return tb;
    }

    /**
     * @return int
     * @throws
     * @title getRowCount
     * @author yefeng
     * @description TODO 帖子的总页数
     */
    public int getRowCount() {
        int rowCount = 0;
        try {
            String sql = " SELECT COUNT(*) FROM topic";
            System.out.println(sql);
            conn = ConnDB.getConnection();
            rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                rowCount = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TopicBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnDB.close(rs, ps, conn);
        }
        return rowCount;
    }

    void close() {
        if (ps != null) {
            try {
                ps.close();
                ps = null;
            } catch (SQLException ex) {
                Logger.getLogger(UserBeanAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException ex) {
                Logger.getLogger(UserBeanAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList getTopicByPage(int pageSize, int currentPage, String searchKey, String searchType) {
        System.out.println("searchKey:" + searchKey);
        System.out.println("searchType:" + searchType);
        ArrayList al = new ArrayList();
        try {
            String sql = "";
            if (searchType.equals("postname")) {
                sql = "select t.id,title,content,topicdate,replycount,likeCount,f.name as forumName,username as postname,forumId,postId from topic t join forum f on t.forumId=f.id\n" +
                        "join user_info u on t.postId=u.id  where u.username like ? limit ?,?";
            } else {
                sql = "select t.id,title,content,topicdate,replycount,likeCount,f.name as forumName,username as postname,forumId,postId from topic t join forum f on t.forumId=f.id\n" +
                        "join user_info u on t.postId=u.id  where " + searchType + " like ? limit ?,?";
            }

            System.out.println(sql);
            conn = ConnDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, searchType);
            ps.setString(1, "%" + searchKey + "%");
            ps.setInt(2, pageSize * (currentPage - 1));
            ps.setInt(3, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                TopicBean tb = new TopicBean();
                tb.setId(rs.getInt("id"));
                tb.setUsername(rs.getString("postname"));
                tb.setTitle(rs.getString("title"));
                tb.setContent(rs.getString("content"));
                tb.setReplycount(rs.getInt("replycount"));
                tb.setTopicdate(rs.getString("topicdate"));
                tb.setLikeCount(rs.getInt("likeCount"));

                tb.setForumName(rs.getString("forumName"));
                tb.setForumId(rs.getInt("forumId"));
                tb.setPostId(rs.getInt("postId"));
                al.add(tb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnDB.close(rs, ps, conn);
        }
        return al;

    }

    public ArrayList getTopicByForumId(int forumId, int pageSize, int currentPage) {
        System.out.println("forumId:" + forumId);
        System.out.println("pageSize:" + pageSize);
        System.out.println("currentPage:" + currentPage);
        ArrayList<TopicBean> al = new ArrayList<>();
        try {
            String sql = "select t.id,title,content,topicdate,replycount,likeCount,f.name as forumName,username as postname,forumId,postId from topic t join forum f on t.forumId=f.id\n" +
                    "join user_info u on t.postId=u.id where forumId= ? limit ?,?";
            System.out.println(sql);
            conn = ConnDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, forumId);
            ps.setInt(2, pageSize * (currentPage - 1));
            ps.setInt(3, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                TopicBean tb = new TopicBean();
                tb.setId(rs.getInt("id"));
                tb.setUsername(rs.getString("postname"));
                tb.setTitle(rs.getString("title"));
                tb.setContent(rs.getString("content"));
                tb.setReplycount(rs.getInt("replycount"));
                tb.setTopicdate(rs.getString("topicdate"));
                tb.setLikeCount(rs.getInt("likeCount"));

                tb.setForumName(rs.getString("forumName"));
                tb.setForumId(rs.getInt("forumId"));
                tb.setPostId(rs.getInt("postId"));
                al.add(tb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TopicBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnDB.close(rs, ps, conn);
        }
        return al;
    }

    private TopicBean getTopicByRS(ResultSet rs) throws SQLException {
        if (rs == null) {
            return null;
        }
        TopicBean tb = new TopicBean();
        tb.setId(rs.getInt("id"));
        tb.setUsername(rs.getString("postname"));
        tb.setTitle(rs.getString("title"));
        tb.setContent(rs.getString("content"));
        tb.setReplycount(rs.getInt("replycount"));
        tb.setTopicdate(rs.getString("topicdate"));
        tb.setLikeCount(rs.getInt("likeCount"));

        tb.setForumName(rs.getString("forumName"));
        tb.setForumId(rs.getInt("forumId"));
        tb.setPostId(rs.getInt("postId"));


        return tb;
    }


    public int getCountBySearch(String searchKey, String searchType) {
        int count = 0;
        try {
            String sql = "";
            if (searchType.equals("postname")) {
                sql = "select count(*) from topic t join forum f on t.forumId=f.id\n" +
                        "join user_info u on t.postId=u.id  where u.username like ?";
            } else {
                sql = "select count(*) from topic t join forum f on t.forumId=f.id\n" +
                        "join user_info u on t.postId=u.id  where " + searchType + " like ?";
            }
            System.out.println(sql);
            conn = ConnDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchKey + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnDB.close(rs, ps, conn);

        }
        return count;
    }

    public int getForumCount(int forumId) {
        int count = 0;
        try {
            String sql = "select count(id) from topic where forumId=?";
            conn = ConnDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, forumId);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TopicBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnDB.close(rs, ps, conn);
        }
        return count;
    }


    public boolean islikeTopic(int userId,int topicId ) {
        boolean flag = false;
        try {
            String sql = "select * from topic_like where tid=? and uid=?";
            conn = ConnDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, topicId);
            ps.setInt(2, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TopicBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnDB.close(rs, ps, conn);
        }
        return flag;
    }

    public int cancelLikeTopic(int userId, int topicId) {
        int flag = -1;
        try {
            String sql = "update topic set likeCount=likeCount-1 where id=?";
            conn = ConnDB.getConnection();

            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, topicId);
             ps.executeUpdate();

           String  sql2 = "delete from topic_like where uid=? and tid=?";
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, userId);
            ps.setInt(2, topicId);
            ps.executeUpdate();


            sql="update user_info u set u.u_likeCount=u.u_likeCount-1\n" +
            "where id=(select postId from topic where id=?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, topicId);
            ps.executeUpdate();
            conn.commit();
            flag=1;
        } catch (SQLException ex) {
            try {
                conn.rollback();
                flag=-2;
            } catch (SQLException e) {
                Logger.getLogger(TopicBeanAction.class.getName()).log(Level.SEVERE, null, e);
                e.printStackTrace();
            }
            Logger.getLogger(TopicBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnDB.close(rs, ps, conn);
        }

        return flag;
    }
}
