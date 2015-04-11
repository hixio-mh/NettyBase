package org.pavelsumarokov.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.netty.handler.codec.http.HttpVersion.*;
import static io.netty.handler.codec.http.HttpHeaders.Names.*;

/**
 * Handler encapsulates BaseServer logic
 */
public class HttpBaseServerHandler
        extends SimpleChannelInboundHandler<FullHttpRequest> implements Responder {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private BaseRouter router;

    private ChannelHandlerContext context;

    public void setRouter(BaseRouter router) {
        this.router = router;
    }

    @Override
    public void channelRead0(ChannelHandlerContext context, FullHttpRequest request) {
        log.info("HTTP Request to: {}", request.getUri());
        this.context = context;
        router.routeRequest(request.getUri(), request.getMethod(), "{}");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        log.error("Exception caught: {}", cause.getMessage());
    }

    public void respond(String json, HttpResponseStatus status) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(json);
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, status,
                Unpooled.copiedBuffer(buffer.toString(), CharsetUtil.UTF_8));
        response.headers().set(CONTENT_TYPE, "application/json; charset=UTF-8");

        context.write(response);
        context.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        context = null;
    }
}
