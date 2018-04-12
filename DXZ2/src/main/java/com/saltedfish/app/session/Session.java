package com.saltedfish.app.session;

import java.util.HashMap;
import java.util.Map;

public class Session {
    private static Map<String,String> session;



    public static Map<String, String> getSession() {
        if (session == null){
            session = new HashMap<>();
        }
        return session;
    }

    public static void add(String timeKey,String token){
        if (searchSession(token) == null)
            session.put(token,timeKey);
        else {
            session.remove(token);
            session.put(token,timeKey);
        }
    }

    public static String searchSession(String token){
        return session.get(token);
    }

    public static void remove(String token){
        session.remove(token);
    }
}
