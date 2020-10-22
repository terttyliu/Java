package 内部类;

/**
 * 创建内部类必须先创建外部类（因为内部类和外部类只见可以直接进行）
 *
 * @author `pjliu`
 * @date 2020/10/01
 */
public class Chapter28_内部类 {
    public static void main(String[] args) {
//        Outer outer = new Outer();
//        outer.fun();
//        Outer.Inner inner = new Outer().new Inner();
        ChannelImpl channel = new ChannelImpl();
        channel.send(channel.new MessageImpl());
    }
}

class Outer {
    private String msg = "OuterClass";
    public Inner inner = new Inner();

    public void fun() {
        System.out.println(inner.msg);
        System.out.println(msg);
        inner.print();
    }

    class Inner {
        private String msg = "InnerClass";

        private void print() {
            System.out.println(msg);
            System.out.println(Outer.this.msg);
        }
    }
}

interface MyChannel {
    /**
     * @param msg 发送的消息
     */
    public void send(Imessage msg);

    interface Imessage {
        public String getContent();
    }
}

class ChannelImpl implements MyChannel{

    @Override
    public void send(Imessage msg) {
        System.out.println("发送消息" + msg.getContent());
    }
    class MessageImpl implements Imessage{

        @Override
        public String getContent() {
            return "pjliu";
        }
    }
}