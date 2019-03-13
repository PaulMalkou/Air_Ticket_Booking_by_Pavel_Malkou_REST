package com.vironit.airticketsbooking.springapp.manager;

import java.util.ResourceBundle;

public class MessageManager {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("messages");

    public static String getMessage(String key) {return BUNDLE.getString(key);}
}
