package example.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by null on 2017/1/18.
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(TimeClientHandler.class);

    private ByteBuf buf;

    public TimeClientHandler() {
        byte[] request = "QUERY TIME ORDER".getBytes();
        buf = Unpooled.buffer(request.length);
        buf.writeBytes(request);
    }

    /**
     * 当客户端和服务端TCP链路建立成功之后，Netty的NIO线程会调用channelActive方法，发送查询时间的的指令给服务端，
     * 调用ChannelHandlerContext的writeAndFlush将请求消息发送给服务端。
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(buf);
    }

    /**
     * 当服务端返回应答消息时，channelRead方法被调用，从中读取消息并打印，然后关闭TCP连接
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf readBuf = (ByteBuf) msg;
        byte[] readBytes = new byte[readBuf.readableBytes()];
        readBuf.readBytes(readBytes);
        String body = new String(readBytes, "UTF-8");
        log.info("now is {}", body);
        ctx.close();
        readBuf.release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
