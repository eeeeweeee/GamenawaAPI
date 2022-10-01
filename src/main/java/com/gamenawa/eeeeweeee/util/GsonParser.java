package com.gamenawa.eeeeweeee.util;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class GsonParser implements IJsonParser{
    private final Gson gson;

    public GsonParser() {
        gson = new Gson();
    }

    @Override
    public String toJson(Object source) {
        return gson.toJson(source);
    }

    @Override
    public <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }
}
