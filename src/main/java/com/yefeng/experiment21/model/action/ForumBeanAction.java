package com.yefeng.experiment21.model.action;

import com.yefeng.experiment21.model.ConnDB;
import com.yefeng.experiment21.model.ForumBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName ForumBeanAction.java
 * @Description TODO
 * @createTime 2022年06月07日 22:08:00
 */
public class ForumBeanAction {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;


//    添加论坛
    public boolean addForum(String forumName){
        boolean flag=false;
        try {
            conn = ConnDB.getConnection();
            String sql = "insert into forum(name) values(?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, forumName);
            int count = ps.executeUpdate();
            if(count>0){
                flag=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForumBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            ConnDB.close(rs, ps, conn);
        }
        return flag;
    }

//    获取论坛列表
    public ArrayList<ForumBean> getForumList(){
        ArrayList<ForumBean> list=new ArrayList<ForumBean>();
        try {
            conn = ConnDB.getConnection();
            String sql = "select * from forum";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                ForumBean forum=new ForumBean();
                forum.setId(rs.getInt("id"));
                forum.setName(rs.getString("name"));
                forum.setCreateDate(rs.getString("createTime"));
                list.add(forum);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForumBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            ConnDB.close(rs, ps, conn);
        }
        return list;
    }

//    删除论坛
    public boolean deleteForum(int forumId){
        boolean flag=false;
        try {
            conn = ConnDB.getConnection();
            String sql = "delete from forum where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, forumId);
            int count = ps.executeUpdate();
            if(count>0){
                flag=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForumBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            ConnDB.close(rs, ps, conn);
        }
        return flag;
    }

//    修改论坛
    public boolean updateForum(int forumId,String forumName){
        boolean flag=false;
        try {
            conn = ConnDB.getConnection();
            String sql = "update forum set name=? where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, forumName);
            ps.setInt(2, forumId);
            int count = ps.executeUpdate();
            if(count>0){
                flag=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForumBeanAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            ConnDB.close(rs, ps, conn);
        }
        return flag;
    }
}
