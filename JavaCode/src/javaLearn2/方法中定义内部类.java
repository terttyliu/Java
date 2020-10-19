package javaLearn2;

/**
 * 方法中定义内部类。
 * JDK1.8 开始支持内部类直接访问外部类中的私有属性和方法的参数。
 * 在JDk1.8之前，想要访问方法的参数或变量，必须追加final。
 * 这是因为1.8中引入了lambda表达式，之所以取消这样的校址，是为了其扩展的函数式编程准备的功能
 *
 * @author `pjliu`
 * @date 2020/10/03
 */
public class 方法中定义内部类 {
    private String msg = "pjliu";

    public void fun(final long time) {
        String a = "Pjliu";
        class Inner {
            public void print() {
                System.out.println(方法中定义内部类.this.msg);
                System.out.println(time);
                System.out.println(a);
            }
        }
        new Inner().print();
    }

    public static void main(String[] args) {
        new 方法中定义内部类().fun(1101013);
    }
}
