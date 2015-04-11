package org.pavelsumarokov.netty;

/**
 * Defines routes for REST API.
 */
public interface BaseRouter {

    public RestApiController controllerForUri(final String uri);
}
