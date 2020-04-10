package com.jxau.m1.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private static InputStream inputStream;
    private static Properties properties = new Properties();
    static{
        inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("url.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String uploadString(String name) {
        return properties.getProperty(name);
    }

    public static String accessString(String access_token) {
        return properties.getProperty(access_token);
    }
}
