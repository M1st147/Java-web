package com.mayikt.dao;

import com.mayikt.entity.StudentEntity;
import com.mayikt.entity.UserEntity;
import com.mayikt.utils.MayiktJdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author M1st
 * @ClassName UserDao
 */
public class UserDao {
    /**
     * 用户的注册
     * 思考点？如果用户一直使用相同的手机号码注册？
     * 该手机号码存在 不需要重复注册？给手机号码----在数据库中不存在
     */
    public int registerUser(UserEntity userEntity) {
        Connection connection = null;
        Statement statement = null;
        try {
            //A.java连接mysql数据库查询所有数据
            //1.导入mysql驱动jar包;
            //2. 注册驱动 javase 反射机制Class.forName()
            connection = MayiktJdbcUtils.getConnection();
            //4. 获取执行者对象
            statement = connection.createStatement();
            //5. 执行sql语句并获取返回结果 executeUpdate执行 insert sql语句
            String insertUserSql = "INSERT INTO `mayikt`.`mayikt_users` (`id`, `phone`, `pwd`) VALUES (null, '" + userEntity.getPhone() + "', '" + userEntity.getPwd() + "');";
            System.out.println("insertStudentSql:" + insertUserSql);
            // log输出
            int result = statement.executeUpdate(insertUserSql);
            // 执行该sql语句 影响行数
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            //  7. 释放jdbc资源
            MayiktJdbcUtils.closeConnection(statement, connection);
        }
    }

    /**
     * 根据手机号码查询用户的信息
     *
     * @param phone
     * @return
     */
    public UserEntity getByPhoneUser(String phone) {
        // 判断用户输入的手机号码是否是为null(正则表达式)
        //phone="";
        if (phone == null || phone.length() == 0) {
            return null;
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //A.java连接mysql数据库查询所有数据
            //1.导入mysql驱动jar包;
            //2. 注册驱动 javase 反射机制Class.forName()
            connection = MayiktJdbcUtils.getConnection();
            //4. 获取执行者对象
            statement = connection.createStatement();
            //5. 执行sql语句并获取返回结果 自己拼接 查询sql语句
            String getByPhoneUserSql = "select * from mayikt_users where phone='" + phone + "'";
            System.out.println(getByPhoneUserSql);
            resultSet = statement.executeQuery(getByPhoneUserSql);
            boolean result = resultSet.next(); // 查询不到数据 false
            // 判断如果查询不到数据 则不会取值
            if (!result) {
                return null;
            }
            //6. 对结果进行处理
            // 获取该行数据的第一列 id
            Long dbId = resultSet.getLong("id");
            // 获取该行数据的第二列 phone
            String dbPhone = resultSet.getString("phone");
            // 获取该行数据的第三列 pwd
            String dbPwd = resultSet.getString("pwd");
            // 将db中查询到数据封装成user对象
            return new UserEntity(dbId, dbPhone, dbPwd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            //  7. 释放jdbc资源
            MayiktJdbcUtils.closeConnection(resultSet, statement, connection);

        }
    }

    /**
     * 用户登录的方法
     *
     * @return
     */
    public UserEntity login(UserEntity userEntity) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //A.java连接mysql数据库查询所有数据
            //1.导入mysql驱动jar包;
            //2. 注册驱动 javase 反射机制Class.forName()
            connection = MayiktJdbcUtils.getConnection();
            //4. 获取执行者对象
            statement = connection.createStatement();
            //5. 执行sql语句并获取返回结果 自己拼接 查询sql语句
            String loginSql = "select * from mayikt_users where phone='" + userEntity.getPhone() + "' and pwd='" + userEntity.getPwd() + "';";
            System.out.println(loginSql);
            resultSet = statement.executeQuery(loginSql);
            boolean result = resultSet.next(); // 查询不到数据 false
            // 判断如果查询不到数据 则不会取值
            if (!result) {
                return null;
            }
            //6. 对结果进行处理
            // 获取该行数据的第一列 id
            Long dbId = resultSet.getLong("id");
            // 获取该行数据的第二列 phone
            String dbPhone = resultSet.getString("phone");
            // 获取该行数据的第三列 pwd
            String dbPwd = resultSet.getString("pwd");
            // 将db中查询到数据封装成user对象
            return new UserEntity(dbId, dbPhone, dbPwd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            //  7. 释放jdbc资源
            MayiktJdbcUtils.closeConnection(resultSet, statement, connection);

        }
    }

}
