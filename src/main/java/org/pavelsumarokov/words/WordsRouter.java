package org.pavelsumarokov.words;

import org.pavelsumarokov.netty.BaseRouter;
import org.pavelsumarokov.netty.RestApiHandler;

/**
 * HTTP Router for Words Web App
 */
public class WordsRouter extends BaseRouter {

    // TODO: configure this using IoC
    private final RestApiHandler onlyOneController = new WordsHandler();

    public RestApiHandler controllerForUri(final String uri) {
        return onlyOneController;
    }
}
