package org.pavelsumarokov.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Basic Netty Server
 */
public class BaseServer {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final int port;

    private final ChannelInitializer channelInitializer;

    public BaseServer(final int port, final ChannelInitializer channelInitializer) {
        this.port = port;
        this.channelInitializer = channelInitializer;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b = b.group(group).channel(NioServerSocketChannel.class);
            setAddressAndHandler(b);
            bindAndCloseSynchronously(b);
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    private void setAddressAndHandler(final ServerBootstrap b) {
        b.localAddress(new InetSocketAddress(this.port))
            .childHandler(channelInitializer);
    }

    private void bindAndCloseSynchronously(final ServerBootstrap b) throws InterruptedException {
        ChannelFuture f = b.bind().sync();
        log.info("{} started on {}", getClass().getName(), f.channel().localAddress());
        f.channel().closeFuture().sync();
    }
}
