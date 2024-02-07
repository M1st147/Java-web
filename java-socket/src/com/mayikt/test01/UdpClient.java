import java.io.IOException;
import java.net.*;

/**
 * @author M1st
 * @ClassName UdpClient
 */
public class UdpClient {
    public static void main(String[] args) throws IOException {
        //1.创建发送端socket对象；
        DatagramSocket datagramSocket = new DatagramSocket();
        //2.提供数据，并将数据封装到数据包中；
        byte[] msg = "mayikt".getBytes();
        InetAddress inetAddress = InetAddress.getByName("test.mayikt.com");
        int port = 8848;
        DatagramPacket datagramPacket = new DatagramPacket(msg, msg.length, inetAddress, port);
        //3.通过socket服务的发送功能，将数据包发出去；
        datagramSocket.send(datagramPacket);
        //4.释放资源；
        datagramSocket.close();
    }
}