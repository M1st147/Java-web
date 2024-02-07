package com.mayikt.test03;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        //创建socket连接Socket
        Socket s = new Socket(InetAddress.getByName("mayikt.server.com"), 8090);
        //获取数据流对象
        OutputStream outputStream = s.getOutputStream();
        String data = "M1st tcp";
        // 写入数据
        outputStream.write(data.getBytes());
        // 关闭资源
        outputStream.close();
        s.close();
    }
}
