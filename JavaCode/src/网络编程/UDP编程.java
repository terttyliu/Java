package 网络编程;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP协议的网络编程
 */
public class UDP编程 {
    @Test
    public void sender()  throws IOException{
        DatagramSocket socket = new DatagramSocket();
        String str="我是UDP方式发送的数据";
        InetAddress host = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(str.getBytes(), 0, str.getBytes().length, host, 18899);
        socket.send(packet);

        socket.close();
    }

    @Test
    public void receiver() throws IOException {
        DatagramSocket socket = new DatagramSocket(18899);
        byte[]buffer=new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);
        socket.receive(packet);
        System.out.println(new String(packet.getData(),0,packet.getLength()).toLowerCase());
    }
}
