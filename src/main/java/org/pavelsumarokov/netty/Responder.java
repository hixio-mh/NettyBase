package org.pavelsumarokov.netty;

import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * Callback to call on completion of non blocking REST API method.
 */
public interface Responder {

    void respond(final String json, final HttpResponseStatus status);
}
