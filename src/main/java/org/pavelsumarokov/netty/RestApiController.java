package org.pavelsumarokov.netty;

import io.netty.handler.codec.http.HttpMethod;

import static io.netty.handler.codec.http.HttpMethod.*;
import static io.netty.handler.codec.http.HttpResponseStatus.*;

/**
 * Declares REST API methods for non blocking controllers.
 */
public abstract class RestApiController {

    // TODO: use automatic marshalling from domain objects
    private static final String UNKNOWN_METHOD = "{\"status\": \"error\", \"cause\": \"Unknown Method\"}";

    private Responder responder;

    public void setResponder(Responder responder) {
        this.responder = responder;
    }

    public abstract void get(final String json);

    public abstract void post(final String json);

    public abstract void put(final String json);

    public abstract void delete(final String json);

    protected void respond(final String json) {
        this.responder.respond(json, OK);
    }

    public void callMethod(final HttpMethod method, final String json) {
        if (POST == method) {
            this.post(json);
        }
        else if (GET == method) {
            this.get(json);
        }
        else if (PUT == method) {
            this.put(json);
        }
        else if (DELETE == method) {
            this.delete(json);
        }
        else {
            this.responder.respond(UNKNOWN_METHOD, NOT_FOUND);
        }
    }
}
