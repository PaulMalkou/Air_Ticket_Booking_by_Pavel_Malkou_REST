package com.vironit.airticketsbooking.springapp.manager;

import java.util.ResourceBundle;

public class PageManager {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("pages");

    public static String getPage(String key) {return BUNDLE.getString(key);}
}
