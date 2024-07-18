package com.scheduler.persistence.setup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SetupDatabase {
    public static void main(String[] args) {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish a connection
            Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");

            // Create a statement
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS schedule (id INTEGER PRIMARY KEY, task TEXT NOT NULL);";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();

            System.out.println("Database setup complete!");

        } catch (Exception e) {
            e.printStackTrace();
        }
}

}
