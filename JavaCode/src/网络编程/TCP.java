package 网络编程;


import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.*;


/**
 * @author pjliu
 */
public class TCP {
    @Test
    public void client() throws IOException {
        OutputStream os = null;
        Socket socket = null;
        socket = new Socket("localhost", 18899);
        os = socket.getOutputStream();
        os.write("This is a test client".getBytes());
        os.flush();
        socket.shutdownOutput();
        //客户端接收数据
        InputStream is = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len=is.read(buffer))!=-1){
            baos.write(buffer,0,len);
        }
        System.out.println(baos.toString());

        os.close();
        socket.close();
        is.close();
        baos.close();

    }

    @Test
    public void server() throws IOException {
        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        OutputStream os = null;
        try {
            //1 创建服务器端Socket，指明自己的端口号
            ss = new ServerSocket(18899);
            //2 调用accept() 表示接收来自客户端的socket
            socket = ss.accept();
            //3 获取输入流
            is = socket.getInputStream();
            //4 读取输入流数据
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            System.out.println(baos.toString());

            //服务器向客户端发送消息
            os = socket.getOutputStream();
            os.write("服务器端成功获取客户端数据".getBytes());
            socket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ss != null) {
                ss.close();
            }
            if (socket != null) {
                socket.close();
            }
            if (baos != null) {
                baos.close();
            }
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }

    }
}
