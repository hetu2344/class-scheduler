package com.scheduler.application;

public class Services {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "het"; // e.g., "root"
    private static final String PASSWORD = "hetu2344";

    public static String getDbURL(){
        return URL;
    }

    public static String getDbUser(){
        return USER;
    }

    public static String getUserPassword(){
        return PASSWORD;
    }
}
