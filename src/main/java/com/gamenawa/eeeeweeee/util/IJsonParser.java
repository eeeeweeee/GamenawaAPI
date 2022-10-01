package com.gamenawa.eeeeweeee.util;

/**
 * Wrapper class for parsing json
 */
public interface IJsonParser {
    String toJson(Object source);
    <T> T fromJson(String json, Class<T> classOfT);
}
