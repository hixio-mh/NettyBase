package org.pavelsumarokov.words;

import org.pavelsumarokov.netty.RestApiController;

/**
 * Sample HTTP REST API Controller
 */
public class WordController extends RestApiController {

    public void get(String json) {
        this.respond("{\"id\": 1, \"value\": \"dictionary\"}");
    }

    public void post(String json) {
        this.respond("{\"id\": 1}");
    }

    public void put(String json) {
        this.respond("{\"id\": 1}");
    }
}
