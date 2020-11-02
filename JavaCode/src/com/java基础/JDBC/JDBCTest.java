package com.java基础.JDBC;

import org.junit.jupiter.api.Test;

/**
 * Java 为JDBC提供有一个模块（java.sql），里面有核心开发包（java.sql）
 * 提供有DriverManager类和若干接口（connection statement preparedstatment ResultSet）
 * JDBC访问数据库的四种方式：
 *     （不推荐）JDCBC-ODBC 桥连接：利用微软ODBC技术进行数据库链接，利用jdbc技术连接odbc进行数据库的开发。
 *      JDBC连接：直接利用JDBC进行数据库的连接处理（适用于本地服务器）
 *      JDBC网络连接：通过特定的网络协议连接指定的数据库（常用）
 *      JDBC协议连接：通过自定义的协议操作实现数据库连接
 */
public class JDBCTest {
    private static final String DATABASE_DRIVER="or";
    @Test
    public void testJDBC1() throws ClassNotFoundException {
        //连接数据库

        Class<?> aClass = Class.forName("com.mysql.jdbc.driver");
    }
}