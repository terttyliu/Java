package com.pjliu.jdbc;

import java.sql.*;

public class CommitTest {
    @org.junit.Test
    public void test() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/ssmbuild?user=root&password=Printf951236";
        String sql = "select * from ssmbuild.books";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            stmt.executeQuery("update ssmbuild.books set bookCounts=100 where bookID>2");
            conn.commit();
            while (rs.next()){
                int bookID = rs.getInt(1);
                String bookName = rs.getString(2);
                int bookCounts = rs.getInt(3);
                String detail = rs.getString(4);
                System.out.printf("%d\t%s\t%d\t%s\n",bookID,bookName,bookCounts,detail);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) conn.close();
                if (stmt == null) stmt.close();
                if (conn == null) rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
