package org.pavelsumarokov;

import org.pavelsumarokov.netty.BaseServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Application entry point.
 * Loads the context and starts the server.
 */
class ServerRunner {
    private static final Logger log = LoggerFactory.getLogger(ServerRunner.class);

    public static void main(String[] args)
    {
        try (ConfigurableApplicationContext context =
                     new ClassPathXmlApplicationContext("wordsContext.xml")) {
            BaseServer server = context.getBean("baseServer", BaseServer.class);
            server.start();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
