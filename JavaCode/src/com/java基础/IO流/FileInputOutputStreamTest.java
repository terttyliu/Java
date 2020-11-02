package com.java基础.IO流;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 无法处理字符文件，一个字符可能占据多个byte，单个字符存在被分割的情况。
 * @author pjliu
 */
public class FileInputOutputStreamTest {
    @Test
    public void testFileInputStream(){
        FileInputStream fis = null;
        try {
            File file = new File("Hello.txt");
            fis = new FileInputStream(file);
            byte[] bytes = new byte[5];
            int len=0;
            while ((len=fis.read(bytes))!=-1){
                String str=new String(bytes,0,len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 实现图片复制操作
     */
    @Test
    public void testFileInputOutputStream(){
        FileOutputStream fos = null;
        FileInputStream fis=null;
        try {
            File srcfile=new File("Hello.txt");
            File destfile = new File("ooo2.txt");
            fis=new FileInputStream(srcfile);
            fos = new FileOutputStream(destfile);
            byte[] bytes=new byte[10];
            int len=0;
            while ((len=fis.read(bytes))!=-1){
                fos.write(bytes,0,len);
            }
            System.out.println("复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
