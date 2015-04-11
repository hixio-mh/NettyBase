package org.pavelsumarokov.netty;

import io.netty.handler.codec.http.HttpMethod;
import org.pavelsumarokov.words.WordController;

import static io.netty.handler.codec.http.HttpResponseStatus.*;

/**
 * Defines routes for REST API.
 */
public class BaseRouter {

    private Responder responder;

    // TODO: configure this using IoC
    private final RestApiController onlyOneController = new WordController();

    public Responder getResponder() {
        return responder;
    }

    public void setResponder(final Responder responder) {
        this.responder = responder;
    }

    public void routeRequest(final String uri, final HttpMethod method, final String json) {
        if ("/word".equalsIgnoreCase(uri) && method != null) {
            callMethod(method);
        }
        else {
            responder.respond("{\"message\": \"Unknown route\"}", NOT_FOUND);
        }
    }

    private void callMethod(HttpMethod method) {
        responder.respond("{\"method\": " + method + "}", OK);
    }
}
