package javaLearn2.Chapter29;

/**
 * 内建函数式接口
 * 如果由开发者自己定义函数式接口，往往都需要使用@FunctionalInterFace 注解进行大量声明，
 * 为了方便则可以直接引用系统中提供的函数式接口。，
 *
 * @author `pjliu`
 * @date 2020/10/03
 */
public class 内建函数式接口 {
    //Function
    public static void main(String[] args) {
        Ifunction6<String, Boolean> myFun = String::startsWith;
        Ifunction3<String> fun3 = String::compareTo;
        System.out.println(fun3.compare("A", "a"));
    }
}

@FunctionalInterface
interface Ifunction6<P,R>{
    public R isStarts(P p1,P p2);
}

@FunctionalInterface
interface Ifunction5<P, R> {
    public R compare(P p1);
}
