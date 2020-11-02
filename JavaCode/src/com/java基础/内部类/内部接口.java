package com.java基础.内部类;

/**
 * 以static定义内部类的形式来讲并不常用，
 * static定义内部接口的形式最为常用.
 * 之所以使用static定义的内部接口，主要是因为这些接口是属于一组相关的定义，
 * 有了外部接口之后可以更加明确的描述出这些接口的主要功能。
 *
 * @author `pjliu`
 * @date 2020/10/03
 */
public class 内部接口 {
    public static void main(String[] args) {
        ImessageWrap.send(new DefaultMessage(), new NetChannel());
    }
}

interface ImessageWrap {
    static interface Imessage {
        public String getContent();
    }

    static interface Ichannel {
        public boolean connect();
    }

    public static void send(Imessage msg, Ichannel channel) {
        if (channel.connect()) {
            System.out.println(msg.getContent());
        } else {
            System.out.println("消息通道无法建立，发送失败");
        }
    }
}

class DefaultMessage implements ImessageWrap.Imessage {

    @Override
    public String getContent() {
        return "pjliu";
    }
}

class NetChannel implements ImessageWrap.Ichannel {

    @Override
    public boolean connect() {
        return false;
    }
}

class Outer4 {
    private static final String MSG = "pjliu";

    static class Inner {
        public void print() {
            System.out.println(Outer4.MSG);
        }
    }
}
