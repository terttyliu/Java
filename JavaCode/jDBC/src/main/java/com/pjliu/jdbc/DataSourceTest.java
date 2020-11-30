package com.pjliu.jdbc;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.function.IntFunction;
import java.util.logging.Logger;

/**
 * 自定义连接池
 * <p>
 * 1：实现DataSource接口
 * 2：重写getConnection方法
 * 3：初始化连接到内存
 * 4：归还连接
 *
 * @author pjliu
 */
public class DataSourceTest {
}

class MyDataPool implements DataSource {
    //存储连接
    private List<Connection> connectionList = new ArrayList<>();

    //初始化连接
    public MyDataPool() {
        for (int i = 0; i < 3; i++) {
            connectionList.add(JDBCUtils.getConnection());
        }
    }

    // 获得连接的方法
    @Override
    public Connection getConnection() throws SQLException {
        if (!connectionList.isEmpty())
            throw new RuntimeException("没有足够的连接了");
        Connection conn = connectionList.remove(0);
        return conn;
    }

    public void backConnection(Connection conn) {
        connectionList.add(conn);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}