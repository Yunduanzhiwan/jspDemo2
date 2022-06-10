package com.yefeng.experiment21.model.action;

import com.yefeng.experiment21.model.ConnDB;
import com.yefeng.experiment21.model.FollowBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName FollowBeanAction.java
 * @Description TODO 关注
 * @createTime 2022年06月07日 10:31:00
 */
public class FollowBeanAction {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

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

    //    添加关注
    public boolean addFollow(FollowBean followBean) {
        String sql = "insert into follow(followUId, uid) values(?,?)";
        boolean flag = false;
        try {
            conn= ConnDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, followBean.getFollowUserId());
            ps.setInt(2, followBean.getUserId());
            ps.executeUpdate();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return flag;
    }
    //    添加关注
    public boolean addFollow(int followUId,int uId) {
        String sql = "insert into follow(followUId, uid) values(?,?)";
        boolean flag = false;
        try {
            conn= ConnDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, followUId);
            ps.setInt(2, uId);
            ps.executeUpdate();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return flag;
    }
    //    取消关注
    public boolean cancelFollow(FollowBean followBean) {
        String sql = "delete from follow where followUId = ? and uid = ?";
        boolean flag = false;
        try {
            conn= ConnDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, followBean.getFollowUserId());
            ps.setInt(2, followBean.getUserId());
            ps.executeUpdate();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return flag;
    }
    //    取消关注
    public boolean cancelFollow(int uId,int followUId) {
        String sql = "delete from follow where followUId = ? and uid = ?";
        boolean flag = false;
        try {
            conn= ConnDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, followUId);
            ps.setInt(2, uId);
            ps.executeUpdate();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return flag;
    }
    //    查询关注过的人
    public List<FollowBean> queryFollow(int userId) {
        String sql = "select * from follow where  uid = ?";
        List<FollowBean> beanList = new ArrayList<>();
        try {
            conn= ConnDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                FollowBean followBean = new FollowBean();
                followBean.setId(rs.getInt("id"));
                followBean.setFollowUserId(rs.getInt("followUId"));
                followBean.setUserId(rs.getInt("uid"));
                followBean.setFollowDate(rs.getString("followDate"));
                beanList.add(followBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return beanList;
    }

    //    查询关注我的人
    public List<FollowBean> queryFollowMe(int userId) {
        String sql = "select * from follow where  followUId = ?";
        List<FollowBean> beanList = new ArrayList<>();
        try {
            conn= ConnDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                FollowBean followBean = new FollowBean();
                followBean.setId(rs.getInt("id"));
                followBean.setFollowUserId(rs.getInt("followUId"));
                followBean.setUserId(rs.getInt("uid"));
                followBean.setFollowDate(rs.getString("followDate"));
                beanList.add(followBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return beanList;
    }

    public boolean isFollow(int userId, int followUserId) {
        System.out.println("userId:"+userId+"followUserId:"+followUserId);
        String sql = "select * from follow where uid = ? and followUId = ?";
        boolean flag = false;
        try {
            conn= ConnDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, followUserId);
            rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
                System.out.println("是关注的人");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        System.out.println("是否关注：" + flag);
        return flag;
    }
}
