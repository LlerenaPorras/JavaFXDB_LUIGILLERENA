package com.project.dbhandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresConnector {
    private final String HOST = "localhost";
    private final String PORT = "5432";
    private final String DB = "Project";
    private final String USER = "luigi";
    private final String PASSWORD = "123456";

    public Connection getConnection() {
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DB;
            connection = DriverManager.getConnection(url, USER, PASSWORD);
            System.out.println("Ooh!!! yeah!!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}