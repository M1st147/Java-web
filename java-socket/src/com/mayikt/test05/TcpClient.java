package com.mayikt.test05;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author M1st
 * @ClassName TcpClient
 */
public class TcpClient {
    public static void main(String[] args) throws IOException {
        // 客户端是可以一直发送数据给服务器端
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入用户的名称：");
            String userName = sc.nextLine();
            System.out.println("请输入用户的密码：");
            String userPwd = sc.nextLine();
            Socket socket = new Socket("127.0.0.1", 8080);
            // 获取到我们的outputStream
            OutputStream outputStream = socket.getOutputStream();
            // 发送数据
            String text = "userName=" + userName + "&userPwd=" + userPwd;
            outputStream.write(text.getBytes());
            // 接受服务器端响应数据给客户端
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len = inputStream.read(bytes);
            String resp = new String(bytes, 0, len
            );
            if ("ok".equals(resp)) {
                System.out.println("登录成功");
            } else {
                System.out.println("登录失败");
            }

            // 关闭资源
            outputStream.close();
            socket.close();
        }
    }
}