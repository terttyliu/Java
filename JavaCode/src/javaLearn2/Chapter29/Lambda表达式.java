package javaLearn2.Chapter29;

/**
 * lambda能够简化代码，避免了复杂的面向对象结构化的要求。
 * 但有一个重要的实现要求:SAM(Single Abstract Method)，只有一个抽象方法
 * 只有满足SAM的接口才是 函数式接口，才能使用Lambda表达式。(default static 不受限制)
 *
 * 可以与Stream配合
 * @author `pjliu`
 * @date 2020/10/03
 */
public class Lambda表达式 {
    public static void main(String[] args) {
        //传统代码样式
//        IMessage msg = new IMessage() {
//            @Override
//            public void send(String str) {
//                System.out.println("发送消息" + str);
//            }
//        };
        IMessage msg = (str) ->
                System.out.println("发送消息" + str);
        msg.send(" this is a Message");
    }
}

@FunctionalInterface
interface IMessage {
    public void send(String str);
}