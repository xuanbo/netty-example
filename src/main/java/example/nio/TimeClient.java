package example.nio;

/**
 * NIO时间服务器客户端
 *
 * Created by null on 2017/1/18.
 */
public class TimeClient {

    public static void main(String[] args) {
        new Thread(new TimeClientHandler("localhost", 8080)).start();
    }

}
