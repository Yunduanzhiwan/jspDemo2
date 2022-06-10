
package com.yefeng.experiment21.model.action;

import com.yefeng.experiment21.model.ConnDB;
import com.yefeng.experiment21.model.ReplyBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author jiangshao
 */
public class ReplyBeanAction {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /**
     * @param topicId      主题id
     * @param userName     用户名
     * @param userId
     * @param replyContent 回复内容
     * @return boolean
     * @throws
     * @title addReply
     * @author yefeng
     * @description TODO 增加回复
     * @updateTime 2022/05/27
     */
    public boolean addReply(String topicId, String userName, int userId, String replyContent) {
        boolean flag = false;
        try {
            String sql = "insert into reply(topicId,userName,userId,replyContent) values(?,?,?,?)";
            System.out.println(sql);
            conn = ConnDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, topicId);
            ps.setString(2, userName);
            ps.setInt(3, userId);
            ps.setString(4, replyContent);
            ps.executeUpdate();
            sql = "update topic set replyCount=replyCount+1 where Id= ?";
            System.out.println(sql);
            ps = conn.prepareStatement(sql);
            ps.setString(1, topicId);
            ps.executeUpdate();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(ReplyBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    /**
     * @param Id          id
     * @param pageSize    页面大小
     * @param currentPage 当前页面
     * @return {@link ArrayList }
     * @throws
     * @title getReplyById
     * @author yefeng
     * @description TODO 获取回复内容
     * @updateTime 2022/05/27
     */
    public ArrayList getReplyById(String Id, int pageSize, int currentPage) {
        ArrayList al = new ArrayList();
        try {
            String sql = "select r.id,r.topicId,r.userName,r.userId,replyContent,replyDate,avatar from reply r join user_info ui on r.userId = ui.id where r.topicId=? limit ?,?";
            System.out.println(sql);
            conn = ConnDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, Id);
            ps.setInt(2, pageSize*(currentPage-1));
            ps.setInt(3, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                ReplyBean rb = new ReplyBean();
                rb.setId(rs.getString("Id"));
                rb.setTopicId(rs.getString("topicId"));
                rb.setUserName(rs.getString("userName"));
                rb.setReplyContent(rs.getString("replyContent"));
                rb.setReplyDate(rs.getString("replyDate"));

                rb.setUserId(rs.getInt("userId"));
                rb.setUserAvatar(rs.getString("avatar"));
                System.out.println(rb);
                al.add(rb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReplyBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return al;
    }

    //获取回复数
    public int getReplyCount(String topicId) {
        int replyCount = 0;
        try {
            String sql = "select count(*) from reply where topicId=?";
            System.out.println(sql);

            conn = ConnDB.getConnection();

            ps = conn.prepareStatement(sql);
            ps.setString(1, topicId);
            rs = ps.executeQuery();
            if (rs.next()) {
                replyCount = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReplyBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return replyCount;
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

}
