package com.pjliu.jdbc;

import java.sql.*;

/**
 * @author pjliu
 */
public class InsertTest {
    public static void main(String[] args) {
        //第一步：加载数据库驱动
        try {
            Class<?> driver = Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("第一步错了");
        //第二步：数据库连接
        Connection conn = null;
        Statement stmt = null;
        int minNo = 1;
        String sql = "insert into books values(bookID.nextval,'鲁迅全集2',100,'爱看看，不看别动我')";
        String sql2 = "insert into books values(bookID.nextval,'鲁迅全集3',100,'爱看看，不看别动我')";
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssmbuild?user=root&password=Printf951236&serverTimezon=UTC");
            //设置手动事务
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {

            }
        } finally {
            // 异常处理
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
