import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @author `pjliu`
 * @date 2020/10/21
 */
public class FileReaderWriterTest {
    public static void main(String[] args) {
        File file = new File("Hello.txt");
        System.out.println(file.getAbsolutePath());
        // /Users/pjliu/Study/Github/JavaCode/Hello.txt
    }
    @Test
    public void FileTest(){
        File file = new File("Hello.txt");
        System.out.println(file.getAbsolutePath());
        // /Users/pjliu/Study/Github/JavaCode/Test/Hello.txt
    }
}
