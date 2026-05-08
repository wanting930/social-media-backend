package com.esun.socialmediaplatform.utils;

import tools.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtils() {}

    public static ObjectMapper getMapper() {
        return objectMapper;
    }

}
