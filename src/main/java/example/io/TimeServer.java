package example.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 同步阻塞I/O服务端
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
            while (true) {
                socket  = serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
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
