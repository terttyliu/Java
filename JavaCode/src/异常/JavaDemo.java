package 异常;


/**
 * Try catch finally 和finally之后代码之间的执行逻辑
 * 代码\异常状态     无异常     catch捕获   catch捕获并抛出    catch未捕捉
 * try             执行       执行中断           执行中断          执行中断
 * catch           不执行     执行               执行         不执行
 * finally         执行       执行               执行         执行
 * 之后的代码        执行       执行               执行        不执行
 *
 * @author `pjliu`
 * @date 2020/09/29
 */
public class JavaDemo {

    static class MyException extends RuntimeException {
        public MyException(String message) {
            super(message);
        }
    }


    public static void eat(int a) throws MyException {
        if (a < 100) {
            throw new MyException("a大于10");
        } else {
            System.out.println(a);
        }
    }

    public static void main(String[] args) {
        eat(10);
    }
}
