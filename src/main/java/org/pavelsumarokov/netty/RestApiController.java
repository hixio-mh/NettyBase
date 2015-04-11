package org.pavelsumarokov.netty;

/**
 * Declares REST API methods for non blocking controllers.
 */
public interface RestApiController {

    String get(String json);
    String post(String json);
    String put(String json);
    String delete(String json);
}
