package org.pavelsumarokov.words;

import org.pavelsumarokov.netty.RestApiHandler;

/**
 * Sample HTTP REST API Handler
 */
public class WordsHandler implements RestApiHandler {

    public String get(String json) {
        return "{\"id\": 1, \"value\": \"dictionary\"}";
    }

    public String post(String json) {
        return "{\"id\": 1}";
    }

    public String put(String json) {
        return "{\"id\": 1}";
    }

    public String delete(String json) {
        return "{\"id\": 1}";
    }
}
