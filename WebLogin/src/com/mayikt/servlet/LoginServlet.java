package com.mayikt.servlet;

import com.mayikt.utils.MayiktJdbcUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author M1st
 * @ClassName LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//1.获取参数
        String userName = req.getParameter("userName");
        String userPwd = req.getParameter("userPwd");
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html;charset=utf-8");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
//2.验证用户名称和密码
            connection = MayiktJdbcUtils.getConnection();
            String loginSql = "SELECT * FROM mayikt_users where userName=? and userPwd=? ";
            preparedStatement = connection.prepareStatement(loginSql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPwd);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String dbUserName = resultSet.getString(1);
                writer.println("恭喜" + userName + "登录成功");
            } else {
                writer.println("登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            writer.println("error");
// 回滚事务
            if (connection != null)
                MayiktJdbcUtils.rollBackTransaction(connection);
        } finally {
            if (writer != null) {
                writer.close();
            }
            MayiktJdbcUtils.closeConnection(null, preparedStatement, connection);
        }
    }
}
