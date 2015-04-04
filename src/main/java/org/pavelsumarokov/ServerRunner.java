package org.pavelsumarokov;

import org.pavelsumarokov.netty.BaseServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerRunner {
    private static Logger log = LoggerFactory.getLogger(ServerRunner.class);

    public static void main(String[] args)
    {
        if (args.length != 1) {
            log.error("Usage: {} <port>", BaseServer.class.getSimpleName());
            return;
        }

        startServerOnPort(Integer.parseInt(args[0]));
    }

    private static void startServerOnPort(int port) {
        try {
            new BaseServer(port).start();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
