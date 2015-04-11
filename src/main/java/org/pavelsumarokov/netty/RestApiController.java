package org.pavelsumarokov.netty;

/**
 * Declares REST API methods for non blocking controllers.
 * Result should be supplied to responder on completion.
 */
public interface RestApiController {

    void get(String json, Responder responder);
    void post(String json, Responder responder);
    void put(String json, Responder responder);
    void delete(String json, Responder responder);
}
