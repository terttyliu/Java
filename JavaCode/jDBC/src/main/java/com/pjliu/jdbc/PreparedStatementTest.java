package com.pjliu.jdbc;

import java.sql.*;

/**
 * 对于sql语句进行优化：变量
 *
 * @author pjliu
 */
public class PreparedStatementTest {
    public static void main(String[] args) {
        //第一步：加载数据库驱动
        try {
            Class<?> driver = Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //第二步：数据库连接
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int minNo = 1;
        String sql = "select bookName,detail,bookID from books where bookID > ?";
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssmbuild?user=root&password=Printf951236&serverTimezon=UTC");
            //创建语句对象
            ps = conn.prepareStatement(sql);
            ps.setInt(1, minNo);
            rs = ps.executeQuery();
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
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
