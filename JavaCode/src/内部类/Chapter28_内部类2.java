package 内部类;

/**
 * @author `pjliu`
 * @date 2020/10/03
 */
public class Chapter28_内部类2 {
    public static void main(String[] args) {
        ChannelIml iml = new ChannelIml();
        iml.send();
    }
}

interface Imessage {
    public void send();

    abstract class AbstractMessage {
        public abstract String getContent();
    }
}

class ChannelIml implements Imessage{

    @Override
    public void send() {
        System.out.println(new MessageImp().getContent());
    }
    class MessageImp extends AbstractMessage{

        @Override
        public String getContent() {
            return "pjliu";
        }
    }
}