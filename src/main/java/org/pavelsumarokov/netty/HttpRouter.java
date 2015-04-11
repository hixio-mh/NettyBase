package org.pavelsumarokov.netty;

import java.util.Map;

/**
 * Defines routes for HTTP REST API.
 */
public class HttpRouter {

    private final Map<String, RestApiController> controllers;

    public HttpRouter(final Map<String, RestApiController> controllers) {
        this.controllers = controllers;
    }

    public RestApiController controllerForUri(final String uri) {
        return controllers.get(uri);
    }
}
