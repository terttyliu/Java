package IO流;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 一、转换流 属于字符流
 * 作用：提供字节流与字符流之间的转换
 * InputStreamReader 将一个字节流转换为字符流 byte[]  --解码格式-->  char[]
 * OutputStreamWriter 将一个字符流转换为字节流 char[] --编码格式--> byte[]
 * <p>
 * 二、字符集
 *
 *
 * @author pjliu
 */
public class 转换流 {
    @Test
    public void testInputStreamReader() {
        InputStreamReader isr = null;
        try {
            FileInputStream fis = new FileInputStream("hello.txt");
            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            char[] cbuf = new char[20];
            int len;
            while ((len = isr.read(cbuf)) != -1) {
                String str = new String(cbuf, 0, len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * UTF-8 ---> GBK
     */
    @Test
    public void testOutputStreamWriter() {
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            isr = new InputStreamReader(new FileInputStream("Hello.txt"), StandardCharsets.UTF_8);
            osw = new OutputStreamWriter(new FileOutputStream("Hello_gbk.txt"), "gbk");
            char[] data = new char[10];
            int len;
            while ((len = isr.read(data)) != -1) {
                osw.write(data, 0, len);
            }
            System.out.println("转码完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
