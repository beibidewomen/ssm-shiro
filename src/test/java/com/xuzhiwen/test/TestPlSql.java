package com.xuzhiwen.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestPlSql {
	public Connection getConn() {  
        String driver = "org.postgresql.Driver";  
        String url = "jdbc:postgresql://10.10.92.233:5432/ssm-test";  
        String username = "postgres";  
        String password = "postgres";  
        Connection conn = null;  
        try {  
            Class.forName(driver); // classLoader,加载对应驱动  
            conn = (Connection) DriverManager.getConnection(url, username, password);  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return conn;  
    }  
	
	public Integer getAll() {  
        Connection conn = getConn();  
        String sql = "select * from user";  
        PreparedStatement pstmt;  
        try {  
            pstmt = (PreparedStatement)conn.prepareStatement(sql);  
            ResultSet rs = pstmt.executeQuery();  
            int col = rs.getMetaData().getColumnCount();  
            System.out.println("============================");  
            while (rs.next()) {  
                for (int i = 1; i <= col; i++) {  
                    System.out.print(rs.getString(i) + "\t");  
                    if ((i == 2) && (rs.getString(i).length() < 8)) {  
                        System.out.print("\t");  
                    }  
                 }  
                System.out.println("");  
            }  
                System.out.println("============================");  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
	public static void main(String[] args) {
		TestPlSql test = new TestPlSql();
		test.getAll();
	}
}
