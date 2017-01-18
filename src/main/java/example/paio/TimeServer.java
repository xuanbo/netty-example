package example.paio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 伪异步I/O服务端
 * TimeServer创建一个线程池，当接收到新的客户端连接后，将请求Socket封装成一个Task
 *
 * Created by null on 2017/1/18.
 */
public class TimeServer {

    private static final Logger log = LoggerFactory.getLogger(TimeServer.class);

    private static final int PORT = 8080;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            log.info("TimeServer start on {} port", PORT);
            Socket socket = null;
            TimeServerHandlerExecutePool singleExecutor =
                    new TimeServerHandlerExecutePool(50, 10000);
            while (true) {
                socket  = serverSocket.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
