package example.nio;

/**
 * NIO时间服务器 TimeServer
 *
 * Created by null on 2017/1/18.
 */
public class TimeServer {

    public static void main(String[] args) {
        new Thread(new MultiplexerTimeServer(8080)).start();
    }

}
