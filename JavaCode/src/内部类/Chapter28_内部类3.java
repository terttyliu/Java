package 内部类;

/**
 * 通过内部类，可以在接口内部实例化接口。内部类接口非常灵活的结构。
 *
 * @author `pjliu`
 * @date 2020/10/03
 */
public class Chapter28_内部类3 {
    public static void main(String[] args) {
        Channel channel = Channel.getInstance();
        channel.send();
    }
}

interface Channel {
    public void send();

    public static Channel getInstance() {
        return new ChannelImpl();
    }

    class ChannelImpl implements Channel {

        @Override
        public void send() {
            System.out.println("pjliu");
        }
    }
}
