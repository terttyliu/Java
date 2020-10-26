package com.IO流;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile
 * 1 直接继承Object 实现了 DataInput 和 DataOutput
 * 2 既可以作为输入流，也可作为输出流
 * 支持"随机访问"的方式。可以从文件的任意位置进行读、写文件
 * 3 若作为输出流时，若文件存在，则会对原有文件内容从头覆盖
 */
public class RandomAccessFileTest {
    @Test
    public void testRandomAccessFile(){
        RandomAccessFile rw = null;
        try {
            rw = new RandomAccessFile("Hello.txt", "rw");
            System.out.println(rw.length());
            int offset=5;
            byte[] data = new byte[(int) (rw.length() - offset)];
            rw.seek(offset);
            rw.readFully(data,0,(int) (rw.length() - offset));
            rw.seek(offset);
            rw.write("e".getBytes());
            rw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (rw!=null) {
                try {
                    rw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
