package 网络编程;

import org.junit.jupiter.api.Test;

import javax.naming.ldap.SortKey;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Arrays;

/**
 * @author pjliu
 */
public class InetAddressTest {
    @Test
    public void client() {
        OutputStream os = null;
        Socket socket=null;
        try {
             socket = new Socket("localhost", 18899);
            os = socket.getOutputStream();
            os.write("This is a test client".getBytes());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void server() throws IOException {
        ServerSocket socket = new ServerSocket(18899);
        Socket accept = socket.accept();
        InputStream is = accept.getInputStream();
        byte[] bytes = is.readAllBytes();
        System.out.println(new String(bytes));

        socket.close();
        accept.close();
    }
}
