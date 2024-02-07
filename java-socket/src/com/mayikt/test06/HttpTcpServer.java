package com.mayikt.test06;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author M1st
 * @ClassName HttpTcpServer
 */
public class HttpTcpServer {
    public static void main(String[] args) throws IOException {
        // 服务器端可以一直接受 客户端数据
        // 创建监听端口号码 ServerSocket
        ServerSocket serverSocket = new ServerSocket(80);
        System.out.println("服务器端启动成功...");
        while (true) {
            // 监听客户端发送过来的数据注意  我们的客户端没有发送数据给服务器端 该方法就会在这里一直阻塞
            Socket socket = serverSocket.accept();
            //不允许单独new线程 线程池来维护线程----java进阶
            new Thread(new Runnable() {
                @Override
                public void run() {
                    InputStream inputStream = null;
                    OutputStream outputStream = null;
                    try {
                        // 获取请求地址
                        byte[] reqByte = new byte[1024];
                        inputStream = socket.getInputStream();
                        outputStream = socket.getOutputStream();
                        int reqLen = inputStream.read(reqByte);
                        String reqUrl = new String(reqByte, 0, reqLen);
                        String url = reqUrl.split("\r\n")[0].split(" ")[1];
                        File file = new File("D:\\M1st\\html" + url);
                        byte[] bytes = new byte[20480];
                        FileInputStream fileInputStream = new FileInputStream(file);
                        //从硬盘中读取数据到内存中
                        int len = fileInputStream.read(bytes);
                        //返回数据给浏览器
                        outputStream.write(bytes, 0, len);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (outputStream != null) {
                            try {
                                outputStream.write("404".getBytes());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    } finally {
                        // 关闭资源
                        try {
                            outputStream.close();
                            inputStream.close();
                            socket.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

}