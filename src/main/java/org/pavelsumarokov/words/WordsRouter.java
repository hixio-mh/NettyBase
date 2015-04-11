package org.pavelsumarokov.words;

import org.pavelsumarokov.netty.BaseRouter;
import org.pavelsumarokov.netty.RestApiController;

/**
 * HTTP Router for Words Web App
 */
public class WordsRouter implements BaseRouter {

    // TODO: configure this using IoC
    private final RestApiController onlyOneController = new WordsController();

    public RestApiController controllerForUri(final String uri) {
        return onlyOneController;
    }
}
