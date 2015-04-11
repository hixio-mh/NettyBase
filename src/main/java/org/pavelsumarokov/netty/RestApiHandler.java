package org.pavelsumarokov.netty;

/**
 * Declares REST API methods for non blocking handlers.
 */
public interface RestApiHandler {

    String get(String json);
    String post(String json);
    String put(String json);
    String delete(String json);
}
