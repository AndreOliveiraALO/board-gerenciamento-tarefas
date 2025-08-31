package com.dio.santander.persistence.config;

import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionConfig {
    
    private ConnectionConfig() {
    }

    private static final String URL = "jdbc:postgresql://localhost:5432/board";
    private static final String USER = "postgres";
    private static final String PASSWORD = "dev2025";

    public static java.sql.Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC do postgresql não encontrado!", e);
        }
        
        var connection = DriverManager.getConnection(URL, USER, PASSWORD);
        connection.setAutoCommit(false);
        return connection;
    }


}
