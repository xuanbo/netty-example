package example.tcp.pk;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * Channel处理
 * 加入计数器，看收到了几次
 *
 * Created by null on 2017/1/18.
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(TimeServerHandler.class);

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        buf.release();
        String body = new String(bytes, "UTF-8")
                .substring(0, bytes.length - System.getProperty("line.separator").length());
        log.info("TimeServer receive order: {}, the counter is {}", body, ++counter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
                ? LocalDateTime.now().toString() : "BAD ORDER";
        ByteBuf respBuf = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(respBuf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
