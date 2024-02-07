package com.mayikt.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author M1st
 * @ClassName IndexServlet
 */
@WebServlet("/mayikt")
public class IndexServlet implements Servlet {

    /**
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * tomcat启动完成
     * 127.0.0.1:8080/项目名称/mayikt 执行 service 通过service方法获取servletRequest、servletResponse
     *
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("mayikt644");
        // 需要通过servletRequest对象获取到客户端传递参数到服务器端
        String userName = servletRequest.getParameter("userName");
        PrintWriter writer = servletResponse.getWriter();
        if ("mayikt".equals(userName)) {
            // 返回数据 ok
            writer.println("ok");
        } else {
            // fail
            writer.println("fail");
        }
        writer.close();// 关闭资源

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
