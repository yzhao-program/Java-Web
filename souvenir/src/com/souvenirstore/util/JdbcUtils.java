package com.souvenirstore.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> connections = new ThreadLocal<>();

    static {
        try {
            Properties properties = new Properties();
            // read jdbc.properties
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 从load data from stream
            properties.load(inputStream);
            // create database connection pool
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * get the connection
     * @return if return null: fail, else get the connection successfully
     */
    public static Connection getConnection(){
        Connection conn = connections.get();
        if (conn == null) {
            try {
                conn = dataSource.getConnection();
                connections.set(conn);
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * commit transaction and close connection
     */
    public static void commitAndClose() {

        Connection conn = connections.get();

        /*
        try {
            if (conn != null) {
                try {
                    conn.commit();
                } finally {
                    conn.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

         */

        DbUtils.commitAndCloseQuietly(conn);

        connections.remove();
    }

    /**
     * rollback transaction and close connection
     */
    public static void rollbackAndClose() {

        Connection conn = connections.get();

        /*
        try {
            if (conn != null) {
                try {
                    conn.rollback();
                } finally {
                    conn.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

         */

        DbUtils.rollbackAndCloseQuietly(conn);

        connections.remove();
    }
}
