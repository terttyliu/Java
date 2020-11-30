package com.pjliu.jdbc;

import java.security.spec.NamedParameterSpec;
import java.sql.*;

public class MyJDBC {
    public static void main(String[] args) {
        //第一步：加载数据库驱动
        try {
            Class<?> driver = Class.forName("com.mysql.cj.ssssjdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("第一步错了");
        //第二步：数据库连接
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssmbuild?user=root&password=Printf951236&serverTimezon=UTC");
            //创建语句对象
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select bookName,detail,bookID from books");
            while (rs.next()) {
                String bookName = rs.getString("bookName");
                String detail = rs.getString("detail");
                int bookID = rs.getInt("bookID");
                System.out.println(bookName + " " + detail + " " + bookID);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 异常处理
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
