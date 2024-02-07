package com.mayikt.test03;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author M1st
 * @ClassName TcpServer
 */

/**
 * 1.创建接收端Socket对象；
 * 2.监听(阻塞：如果建立连接失败，程序会一直阻塞，不往下执行)
 * 3.获取输入流对象
 * 4.获取数据
 * 5.输出数据
 * 6.释放资源
 */

public class TcpServer {
    public static void main(String[] args) throws IOException {
        // 创建serverSocket
        ServerSocket serverSocket = new ServerSocket(8090);
        // 监听客户端数据
        Socket socket = serverSocket.accept();
        // 获取到客户端发送的数据
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = inputStream.read(bytes);
        System.out.println("客户端发送的数据:" + new String(bytes, 0, len));
        // 关闭资源
        inputStream.close();
        serverSocket.close();

    }
}