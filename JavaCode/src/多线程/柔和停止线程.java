package 多线程;

/**
 *
 * 使用flag控制A线程执行，当其他线程设置flag后。A线程不会立即停止，而是会在下一次判断flag时停止。
 * @author `pjliu`
 * @date 2020/10/08
 */
public class 柔和停止线程 {
    public static boolean flag=true;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            long num = 0;
            while (flag) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":num=" + num++);
            }
        },"执行线程").start();
        Thread.sleep(100);
        flag=false;
    }
}
