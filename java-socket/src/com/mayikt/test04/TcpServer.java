package com.mayikt.test04;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

/**
 * @author M1st
 * @ClassName TcpServer
 */

public class TcpServer {
    public static void main(String[] args) throws IOException {
        // 创建serversocket对象
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务器端启动成功....");
        while (true) {
            // 接受客户端数据
            Socket socket = serverSocket.accept();
            //inputStream
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len = inputStream.read(bytes);
            System.out.println("服务器端接受客户端：" + new String(bytes, 0, len));
            // 服务端响应数据给客户端
            OutputStream outputStream = socket.getOutputStream();
            String resp = "我收到啦" + UUID.randomUUID().toString();
            outputStream.write(resp.getBytes());
            inputStream.close();
            outputStream.close();
            socket.close();
        }
    }
}