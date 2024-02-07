package com.mayikt.test02;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {
    public static void main(String[] args) throws IOException {
        //创建DatagramSocket发送者
        DatagramSocket ds = new DatagramSocket();
        //创建Scanner
        while (true) {
            System.out.println("客户端：请输入发送的内容");
            Scanner sc = new Scanner(System.in);
            String context =sc.nextLine();
            if ("666".equals(context)) {
                System.out.println("退出程序...");
                break;
            }
            byte[] data = context.getBytes();
            //封装数据包
            DatagramPacket dp = new DatagramPacket(data, data.length, InetAddress.getByName("mayikt.server.com"),8080);

        ds.send(dp);
        System.out.println("发送数据成功...");
        }
        ds.close();
    }
}
