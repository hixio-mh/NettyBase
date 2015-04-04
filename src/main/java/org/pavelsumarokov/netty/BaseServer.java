package org.pavelsumarokov.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import io.netty.channel.socket.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Basic Netty Server
 */
public class BaseServer {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final int port;

    public BaseServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            setAddressAndHandler(b.group(group).channel(NioServerSocketChannel.class));
            bindAndCloseSynchronously(b);
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    private void setAddressAndHandler(final ServerBootstrap b) {
        b.localAddress(new InetSocketAddress(this.port))
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new BaseServerHandler());
                }
            });
    }

    private void bindAndCloseSynchronously(final ServerBootstrap b) throws InterruptedException {
        ChannelFuture f = b.bind().sync();
        log.info("{} started on {}", getClass().getName(), f.channel().localAddress());
        f.channel().closeFuture().sync();
    }
}
