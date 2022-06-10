/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yefeng.experiment21.model.action;

import com.yefeng.experiment21.model.ConnDB;
import com.yefeng.experiment21.model.UserBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author 97549
 */
public class UserBeanAction {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    //注册-增加数据
    public boolean regUser(String username, String password, String sex, String email, String phone, String avater) {
        boolean flag = false;
        try {
            conn = ConnDB.getConnection();
            String sql = "INSERT INTO user_info(username,passwd,sex,email,phone,avatar)"
                    + " VALUES(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, sex);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, avater);
            ps.executeUpdate();
            System.out.println("注册成功");
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    //登录
    public boolean login(String password, String account) {
        boolean flag = false;
        try {
            conn = ConnDB.getConnection();
            String sql = "select *from user_info where (username=? or phone=? or email=?) and passwd=?";
            System.out.println(sql);
            ps = conn.prepareStatement(sql);
            ps.setString(1, account);
            ps.setString(2, account);
            ps.setString(3, account);
            ps.setString(4, password);
            rs = ps.executeQuery();
            if (rs.next())
                flag = true;
            close();

        } catch (SQLException ex) {
            Logger.getLogger(UserBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
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


    //注册-检验手机号或者邮箱或者用户名是否注册过
    public boolean checkUser(String account) {//account为用户名或者邮箱或者手机号  phone/email/username
        boolean flag = true;
        //如果account不为phone/email/username，则为参数不合法，返回false
        if (!account.equals("phone") && !account.equals("email") && !account.equals("username")) {
            flag = false;
        }
        try {
            conn = ConnDB.getConnection();
            String sql = "select * from user_info where " + account + "=?";//account动态参数，为用户名或者邮箱或者手机号  phone/email/username
            System.out.println(sql);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, account);
            rs = ps.executeQuery();
            if (rs.next()) {
                flag = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
        return flag;
    }

    //根据手机号查找用户信息
    public UserBean findUserByUserName(String userName) {
        UserBean user = new UserBean();
        try {
            conn = ConnDB.getConnection();
            String sql = "select * from user_info where username='" + userName + "'";
            System.out.println(sql);
            rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("passwd"));
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setSex(rs.getString("sex"));
                //return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
        return user;
    }

    //根据手机号查找用户信息
    public UserBean findUser(String parem) {
        UserBean user = new UserBean();
        try {
            conn = ConnDB.getConnection();
            String sql = "select * from user_info where username='" + parem + "' or phone='" + parem + "' or email='" + parem + "'";
            System.out.println(sql);
            rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("passwd"));
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setSex(rs.getString("sex"));
                //return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
        return user;
    }

    //根据手机号查找用户信息
    public UserBean findUserByPhone(String Phone) {
        UserBean user = new UserBean();
        try {
            conn = ConnDB.getConnection();
            String sql = "select *from user_info where phone='" + Phone + "'";
            System.out.println(sql);
            rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("passwd"));
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setSex(rs.getString("sex"));
                //return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
        return user;
    }

    public UserBean getUserInfo(int userId) {
        UserBean user = null;
        try {
            conn = ConnDB.getConnection();
            String sql = "select * from user_info where id=" + userId;
            rs = conn.createStatement().executeQuery(sql);
            if (rs.next()) {
                user= new UserBean();
                user.setPhone(rs.getString("phone"));
//                user.setPassword(rs.getString("passwd"));
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setSex(rs.getString("sex"));
                user.setAvatar(rs.getString("avatar"));

                user.setLikeCount(rs.getInt("u_likeCount"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}

