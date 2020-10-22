package IO流;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Objects;

/**
 * 这里主要介绍 标准输入、输出流 打印流 数据流 对象流（重要） 随机存取文件流
 */
public class 其他流 {
    /**
     * 1 标准输入、输出流
     * System.in  默认从键盘输入
     * System.out 默认从控制台输出
     * 通过SetIn(InputStream in) 修改输入输出流。
     */
    @Test
    public void test标准输入输出流() {
        //System.in => BufferedReader的readline()
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            String data = null;
            while (true) {
                System.out.println("请输入字符串");
                data = br.readLine();
                if (Objects.equals(data, "e") || Objects.equals(data, "exit")) {
                    break;
                }
                System.out.println(data.toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 2 打印流 PrintStream 和 PrintfWriter
     * 作用：实现将基本数据类型的数据格式转化为字符串输出
     * 功能：提供了一系列重载的Print()和Println()
     * 实例：System.out
     */
    @Test
    public void testPrintStreamWriter() {
        FileOutputStream fos = null;
        PrintStream ps = null;
        try {
            fos = new FileOutputStream(new File("Hello.txt"), true);
            //自动刷新模式（写入换行符或者'\n'都会刷新输出缓冲区）
            ps = new PrintStream(fos, true);
            if (ps != null) {
                System.setOut(ps);
            }
            for (int i = 0; i < 255; i++) {
                System.out.print((char) i);
                if (i % 50 == 0) {
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                ps.close();
            }
        }
    }

    /**
     * 3 数据流 DataInPutStream 和 DataOutputStream
     * 作用：允许应用程序以与机器无关方式从底层输入流中读取或输出基本Java数据类型
     */
    @Test
    public void testDataInputOutputStream() {
        DataOutputStream dos = null;
        DataInputStream dis = null;
        try {
            dos = new DataOutputStream(new FileOutputStream("data.data"));
            dis = new DataInputStream(new FileInputStream("data.data"));
            dos.writeUTF("刘鹏杰");
            dos.writeInt(23);
            dos.writeBoolean(true);
            dos.flush();

            String name = dis.readUTF();
            Integer age = dis.readInt();
            String sex = dis.readBoolean() ? "男" : "女";
            System.out.printf("姓名：%s 年龄：%d 性别：%s \n",name,age,sex);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dis!=null){
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
