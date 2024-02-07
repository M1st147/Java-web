package com.mayikt.test02;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpServer {
    static  int count;

    public static void main(String[] args) throws IOException {
        //服务器端一直接受客户端发送的数据
        //创建接受Socket对象
        DatagramSocket ds = new DatagramSocket(8080);
        while (true) {
            //创建接收者数据包
            if(count==10000){ //存在线程安全性问题
                break;
            }
            byte[] bytes = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
            ds.receive(dp);
            System.out.println("服务器端接受到客户端发送的数据："+new String(dp.getData()));
            count++;
        }
        ds.close();
    }
}
