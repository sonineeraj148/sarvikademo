package com.example.sarvikademo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Util {

    public static String getObjectToJson(Object object) {

        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        Gson gson = builder.create();
        return gson.toJson(object);
    }

    public static boolean isEmpty(String value) {
        return value == null || value.equals("");
    }
}
