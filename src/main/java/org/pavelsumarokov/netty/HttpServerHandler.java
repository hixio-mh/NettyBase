package org.pavelsumarokov.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static io.netty.handler.codec.http.HttpVersion.*;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpHeaders.Names.*;

/**
 * Handler encapsulates BaseServer logic
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<Object> {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void channelRead0(ChannelHandlerContext context, Object msg) {
        if (!(msg instanceof FullHttpRequest)) {
            log.warn("Incomplete message: {}", msg);
            return;
        }

        FullHttpRequest request = (FullHttpRequest) msg;
        log.info("HTTP Request to: {}", request.getUri());
        writeResponse(request, context);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        log.error("Exception caught: {}", cause.getMessage());
    }

    private void writeResponse(FullHttpRequest request, ChannelHandlerContext context) {
        StringBuffer buffer = new StringBuffer();
        appendGreeting(buffer, request);
        appendHeaders(buffer, request);
        context.write(composeResponse(buffer));
        context.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    private void appendGreeting(final StringBuffer buffer, final FullHttpRequest request) {
        buffer.append("Welcome to Basic Netty Server.\r\n");
        buffer.append("==============================\r\n");
        buffer.append("REQUEST_URI: ").append(request.getUri()).append("\r\n\r\n");
    }

    private void appendHeaders(final StringBuffer buffer, final FullHttpRequest request) {
        HttpHeaders headers = request.headers();
        if (!headers.isEmpty()) {
            for (Map.Entry<String, String> header: headers) {
                String key = header.getKey();
                String value = header.getValue();
                buffer.append("HEADER: ").append(key).append(": ").append(value).append("\r\n");
            }
            buffer.append("\r\n");
        }
    }

    private HttpResponse composeResponse(final StringBuffer buffer) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK,
                Unpooled.copiedBuffer(buffer.toString(), CharsetUtil.UTF_8));
        response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
        return response;
    }
}
