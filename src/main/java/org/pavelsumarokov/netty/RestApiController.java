package org.pavelsumarokov.netty;

import io.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.netty.handler.codec.http.HttpMethod.*;
import static io.netty.handler.codec.http.HttpResponseStatus.*;

/**
 * Declares REST API methods for non blocking controllers.
 */
class RestApiController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    // TODO: use automatic marshalling from domain objects
    private static final String UNKNOWN_METHOD = "{\"status\": \"error\", \"cause\": \"Unknown Method\"}";
    private static final String UNSUPPORTED_METHOD = "{\"status\": \"error\", \"cause\": \"Method is not supported\"}";

    private Responder responder;

    public void setResponder(Responder responder) {
        this.responder = responder;
    }

    /**
     * Sends response back to a client.
     */
    protected void respond(final String json) {
        this.responder.respond(json, OK);
    }

    /**
     * Should be overridden if supported.
     */
    protected void get(final String json) {
        log.error("Unsupported GET with {}", json);
        this.responder.respond(UNSUPPORTED_METHOD, METHOD_NOT_ALLOWED);
    }

    /**
     * Should be overridden if supported.
     */
    protected void post(final String json) {
        log.error("Unsupported POST with {}", json);
        this.responder.respond(UNSUPPORTED_METHOD, METHOD_NOT_ALLOWED);
    }

    /**
     * Should be overridden if supported.
     */
    protected void put(final String json) {
        log.error("Unsupported PUT with {}", json);
        this.responder.respond(UNSUPPORTED_METHOD, METHOD_NOT_ALLOWED);
    }

    /**
     * Should be overridden if supported.
     */
    protected void delete(final String json) {
        log.error("Unsupported DELETE with {}", json);
        this.responder.respond(UNSUPPORTED_METHOD, METHOD_NOT_ALLOWED);
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
