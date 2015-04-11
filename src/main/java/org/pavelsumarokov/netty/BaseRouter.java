package org.pavelsumarokov.netty;

import io.netty.handler.codec.http.HttpMethod;

import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpMethod.*;

/**
 * Defines routes for REST API.
 */
public abstract class BaseRouter {

    // TODO: use automatic marshalling from domain objects
    private static final String UNKNOWN_URI = "{\"status\": \"error\", \"cause\": \"Unknown Uri\"}";
    private static final String UNKNOWN_METHOD = "{\"status\": \"error\", \"cause\": \"Unknown Method\"}";

    private Responder responder;

    public void setResponder(final Responder responder) {
        this.responder = responder;
    }

    public abstract RestApiController controllerForUri(final String uri);

    public void routeRequest(final String uri, final HttpMethod method, final String json) {
        RestApiController controller = controllerForUri(uri);

        if (null == controller) {
            responder.respond(UNKNOWN_URI, NOT_FOUND);
        }

        String result = callMethod(controller, method, json);
        responder.respond(result, OK);
    }

    private String callMethod(final RestApiController controller,
                              final HttpMethod method,
                              final String json) {
        if (POST == method) {
            return controller.post(json);
        }
        if (GET == method) {
            return controller.get(json);
        }
        if (PUT == method) {
            return controller.put(json);
        }
        if (DELETE == method) {
            return controller.delete(json);
        }
        return UNKNOWN_METHOD;
    }
}
