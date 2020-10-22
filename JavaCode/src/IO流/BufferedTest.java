package IO流;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 处理流之一：缓冲流
 * BufferedInputStream
 * BufferedOitputStream
 * BufferedReader
 * BufferedWriter
 * 意义：提高流的读取，写入速度
 * 原因：内部提供了缓冲
 * @author pjliu
 */
public class BufferedTest {
    @Test
    public void testBufferedStream(){
        BufferedInputStream bis=null;
        BufferedOutputStream bos=null;

        try {
            File srcfile = new File("Hello.txt");
            File destfile = new File("o.txt");
            FileInputStream fis = new FileInputStream(srcfile);
            FileOutputStream fos = new FileOutputStream(destfile);
            //造缓冲流
             bis = new BufferedInputStream(fis);
             bos = new BufferedOutputStream(fos);
            //复制细节：读取写入
            byte[]buffer=new byte[10];
            int len=0;
            while ((len=bis.read(buffer))!=-1){
                bos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis!=null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos!=null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
