package example.aio;

/**
 * AIO创建的TimeClient
 *
 * Created by null on 2017/1/18.
 */
public class TimeClient {

    public static void main(String[] args) {
        new Thread(new AsyncTimeClientHandler("localhost", 8080)).start();
    }
}
