/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yefeng.experiment21.model;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ConnDB {
    private static Connection conn;
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/jsp?useUnicode=true&characterEncoding=UTF8",
                            "root", "jiangshao888");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    public static void main(String[] args) {
        System.out.println(getConnection());
    }

    public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
