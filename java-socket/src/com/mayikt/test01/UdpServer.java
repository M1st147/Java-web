
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author M1st
 * @ClassName UdpServer
 */
public class UdpServer {
    public static void main(String[] args) throws IOException {
        //创建接收端socket对象
        DatagramSocket datagramSocket = new DatagramSocket(8848);
        //接收数据；
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        //解析数据；
        System.out.println("开始接受数据.");
        datagramSocket.receive(datagramPacket);
        System.out.println("接受到数据.");
        //输出数据；
        String msg = new String(datagramPacket.getData());
        System.out.println(msg);
        //释放资源；
        datagramSocket.close();

    }
}
