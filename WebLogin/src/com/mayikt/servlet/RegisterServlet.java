package com.mayikt.servlet;

import com.mayikt.utils.MayiktJdbcUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author M1st
 * @ClassName RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html;charset=utf-8");
        //1.获取参数
        String userName = req.getParameter("userName");
        if (StringUtils.isEmpty(userName)) {
            writer.print("userName不能为空!");
            return;
        }
        String userPwd = req.getParameter("userPwd");
        if (StringUtils.isEmpty(userPwd)) {
            writer.print("userPwd不能为空!");
            return;
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {

            connection = MayiktJdbcUtils.getConnection();
            //2.验证用户名称和密码
            MayiktJdbcUtils.beginTransaction(connection);// 开启事务
            String insertSql = "INSERT INTO `mayikt`.`mayikt_users` (`id`, `userName`, `userPwd`) VALUES (null, ?,?); ";
            preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPwd);
            int insertResult = preparedStatement.executeUpdate();
            MayiktJdbcUtils.commitTransaction(connection);
            String result = insertResult > 0 ? "注册成功" : "注册失败";
            writer.println(result);
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
