package com.pjliu.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.pjliu.jdbc.DataSourceTest;
import com.pjliu.jdbc.JDBCUtils;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author pjliu
 */
public class DruidDemo01 {
    @Test
    public void test(){
        // Connection conn=null;

    }
}
