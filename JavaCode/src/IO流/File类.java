package IO流;


import java.io.File;
import java.time.Instant;
import java.util.Scanner;

/**
 * 1. File类的一个对象，代表一个文件或一个文件目录(俗称：文件夹)
 * 2. File类声明在java.io包下
 * 3. File类中涉及到关于文件或文件目录的创建、删除、重命名、修改时间、文件大小等方法，
 * 并未涉及到写入或读取文件内容的操作。如果需要读取或写入文件内容，必须使用IO流来完成。
 * 4. 后续File类的对象常会作为参数传递到流的构造器中，指明读取或写入的"终点".
 *
 * @author `pjliu`
 * @date 2020/10/18
 */
public class File类 {
    public static void main(String[] args) {
        /*
         创建File类实例
         */
        File file1 = new File("hello.txt");
        File file2 = new File(System.getProperty("user.dir") + File.separator + "hello.txt");
        //存在于内存中。
        System.out.println("file1:" + file1);
        System.out.println("file2:" + file2);
        //指定为某个文件目录
        File file3 = new File(System.getProperty("user.dir"), "aNewFile");
        System.out.println("file3:" + file3);
        File file4 = new File(file3, "aNewFile2");
        System.out.println("*****************************");

        //常用方法测试
        // File类的获取功能
        // public String getAbsolutePath()：获取绝对路径
        // public String getPath() ：获取路径
        // public String getName() ：获取名称
        // public String getParent()：获取上层文件目录路径。若无，返回null
        // public long length() ：获取文件长度（即：字节数）。不能获取目录的长度。
        // public long lastModified() ：获取最后一次的修改时间，毫秒值


        System.out.println("file1:" + file1.getAbsolutePath());
        System.out.println("file2:" + file2.getName());
        System.out.println("file1:" + file1.getParent());
        System.out.println(file1.length());
        System.out.println(Instant.ofEpochMilli(file1.lastModified()));

        //适用于文件目录
        // public String[] list() ：获取指定目录下的所有文件或者文件目录的名称数组
        // public File[] listFiles() ：获取指定目录下的所有文件或者文件目录的File数
        File file5 = new File("com/集合");
        File[] files = file5.listFiles();
        fun(files);

        //public boolean renameTo(File dest):把文件重命名为指定的文件路径
        //file1要存在于硬盘中，形参要不存在。
        System.out.println(file1.renameTo(new File("hello2.txt")));

        System.out.println("************");
        // public boolean isDirectory()：判断是否是文件目录
        // public boolean isFile() ：判断是否是文件
        // public boolean exists() ：判断是否存在于硬盘中
        // public boolean canRead() ：判断是否可读
        // public boolean canWrite() ：判断是否可写
        // public boolean isHidden() ：判断是否隐藏
        File file6 = new File(System.getProperty("user.dir") +File.separator+ "cc");
        System.out.println(file6.isDirectory());
        System.out.println(file6.isFile());
    }

    public static void fun(File[] files) {
        for (File file : files) {
            if (file.isDirectory()) {
                fun(file.listFiles());
            }else {
                System.out.printf("%s 文件大小为：%.2f KB \n",file,(file.length()/1024.0));
                Scanner scanner = new Scanner(System.in);
            }
        }
    }
}

