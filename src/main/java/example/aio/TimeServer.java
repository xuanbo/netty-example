package example.aio;

/**
 * AIO时间服务端
 *
 * Created by null on 2017/1/18.
 */
public class TimeServer {

    public static void main(String[] args) {
        new Thread(new AsyncTimeServerHandler(8080)).start();
    }

}
