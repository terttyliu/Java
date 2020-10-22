package IO流;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 字节流结尾为Stream 字符流结尾为Reader或者Writer
 * 一、流的分类
 * 1 单位：字节流、字符流
 * 2 数据的流向：输入流、输出流
 * 3 流的角色：节点流、处理流
 * <p>
 * 二、流的体系结构
 * 抽象基类             节点流（文件流）         缓冲流（处理流的一种）
 * InputStream         FileInputStream      BufferedInputStream
 * OutputStream        FileOutputStream     BufferedOOutputStream
 * Reader              FileReader           BufferedReader
 * Writer              FileWriter           BufferedWriter
 *
 * @author `pjliu`
 * @date 2020/10/18
 */
public class FileReaderWriter {
    public static void main(String[] args) {
        File file = new File("Hello.txt");
        System.out.println(file.getAbsolutePath());//位于当前工程下。
    }

    /**
    将Test下的Hello.txt读入程序中，并输出到控制台。

    主要步骤:
    1 File 类实例化
    2 流实例化
    3 操作
    4 关闭流
    说明:
    1 异常处理: 为了保证流的关闭，必须使用try-catch-finally
    2 读入的文件必须存在，否则会报错 FileNotFoundException
    3 fr.read() 每次读取一个字符
     */
    @Test //单元测试归每个module所有
    public void testFileReader() {
        FileReader fr = null;
        try {
            //1 实例化File对象
            File file = new File("Hello.txt");
            System.out.println(file.getAbsolutePath());//位于当前module下。
            //2 提供具体流
            fr = new FileReader(file);
            //3 文件读入
            //read()返回读入的一个字符，若达到文件末尾，返回-1
            int data = 0;
            while ((data = fr.read()) != -1) {
                System.out.print((char) data);
            }
            //4 流的关闭操作 对于物理连接，JVM无法进行垃圾回收。
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对read()操作升级, 使用重载方法。
     */
    @Test
    public void testFileReader1(){
        FileReader fr = null;
        try {
            //1 File实例化
            File file = new File("Hello.txt");
            //2 FileReader实例化
            fr = new FileReader(file);
            //3 读取操作

            //3.1 返回读入char[]中的字符个数
            char[] cbuff=new char[10];
            int len = fr.read(cbuff);
            while (len!=-1){
            // 错误写法：i <cbuff.length
            // for (int i = 0; i <cbuff.length; i++) {
                for (int i = 0; i <len; i++) {
                    System.out.print(cbuff[i]);
                }
                len =fr.read(cbuff);
            }
            //3.2

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4 资源关闭
            if (fr!=null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从内存写入到硬盘中
     */
    @Test
    public void testFileWriter(){
        FileWriter fw = null;
        try {
            File file = new File("hello1.txt");
            fw = new FileWriter(file,false);
            fw.write("I have a dream.\n");
            fw.write("You also need a Dream.\n");
            fw.write("Write by 刘鹏杰\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw!=null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 不能用字符流处理图片音频等字节文件
     */
    @Test
    public void testFileReaderWriter(){
        FileReader fr = null;
        FileWriter fw = null;
        try {
            File srcFile = new File("Hello.txt");
            File destFile = new File("ooo.txt");

            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);

            char[] buf =new char[10];
            int len=0;
            while ((len=fr.read(buf))!=-1){
                    fw.write(buf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr!=null){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fw!=null){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
