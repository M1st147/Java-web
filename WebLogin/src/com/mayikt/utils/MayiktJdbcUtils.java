package com.mayikt.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author M1st
 * @ClassName MayiktJdbcUtils
 */
public class MayiktJdbcUtils {
    /**
     * 1.需要将我们的构造方法私有化 ---工具类 不需要 new出来 是通过类名称.方法名称访问
     */
    private MayiktJdbcUtils() {

    }

    /**
     * 2.定义工具类 需要 声明 变量
     */
    private static String driverClass;
    private static String url;
    private static String user;
    private static String password;

    /**
     *3.使用静态代码快 来给我们声明好 jdbc变量赋值（读取config.properties）
     */
    static {
        try {
            // 1.读取config.properties  IO 路径 相对路径
            InputStream resourceAsStream = MayiktJdbcUtils.class.getClassLoader().
                    getResourceAsStream("config.properties");
            // 2.赋值给我们声明好的变量
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            driverClass = properties.getProperty("driverClass");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            // 3.注册驱动类
            Class.forName(driverClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 4.封装连接方法
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    /**
     * 5.封装释放连接方法 (重载)
     */
    public static void closeConnection(ResultSet resultSet, Statement statement, Connection connection) {
        // 1.查询 释放连接 resultSet  statement connection
        try {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 增删改---释放jdbc资源
     *
     * @param statement
     * @param connection
     */
    public static void closeConnection(Statement statement, Connection connection) {
        // 1.查询 释放连接 resultSet  statement connection
        closeConnection(null, statement, connection);
    }

    /**
     * 开启事务
     *
     * @param connection
     * @throws SQLException
     */
    public static void beginTransaction(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
    }

    /**
     * 提交事务
     *
     * @param connection
     * @throws SQLException
     */
    public static void commitTransaction(Connection connection) throws SQLException {
        connection.commit();
    }

    /**
     * 回滚事务
     *
     * @param connection
     */
    public static void rollBackTransaction(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭事务
     *
     * @param connection
     */
    public static void endTransaction(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
