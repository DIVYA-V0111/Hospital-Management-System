package com.javapro;

import java.sql.*;

public class DBConnection {

    static Connection con;

    public static Connection getConnection() {
        try {
            if (con == null) {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/hospital",
                        "root",
                        System.getenv("DB_PASSWORD")
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return con;
    }
}