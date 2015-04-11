package org.pavelsumarokov.words;

import org.pavelsumarokov.netty.RestApiController;

/**
 * Sample HTTP REST API Controller
 */
public class WordsController implements RestApiController {

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
