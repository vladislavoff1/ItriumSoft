package com.itrium.borey.client;


import java.sql.*;
import java.io.PrintWriter;


/**
 * @author Vladislav Romanov
 */

//TODO: Connection pool.

public class Authorization {

    protected static String driverName = "com.mysql.jdbc.Driver";
    protected static String url = "jdbc:mysql://localhost:3306/IPControllerDB";
    protected static String name = "root";
    protected static String password = "vlad007.1996@yandex.ru";

    private static String signInQuery = "SELECT * FROM  `Users` WHERE  `email` LIKE  '%email' AND  `password` LIKE  '%password'";
    // TODO: SQL Injection.

    public static int signIn(String email, String pass) {

        // TODO: Email and password are can't to consist some symbols. E.g. '?', '"', ';'. 

        Connection connection = null;
        ResultSet result;
        int res = -1;

        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();

            String query = signInQuery.replaceAll("%email", email).replaceAll("%password", pass);
            result = statement.executeQuery(query);

            res = result.next() ? result.getInt("id") : -1;
        } catch (Exception e) {
            // TODO: Logger.
            return -1;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO: Logger.
                }
            }
        }
        return res; 
    }

    private static String testEmailQuery = "SELECT * FROM  `Users` WHERE  `email` LIKE  '%email'";
    private static String signUpQuery = "INSERT INTO  `Users` (`email` ,`password`) VALUES ('%email',  '%password');";

    public static boolean signUp(String email, String pass, PrintWriter out) {
        
        // TODO: Email and password are can't to consist some symbols. E.g. '?', '"', ';'. 

        Connection connection = null;
        ResultSet result;
        int res = -1;

        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();

            String query = testEmailQuery.replaceAll("%email", email);
            result = statement.executeQuery(query);   

            if (result.next()) {
                // TODO: Throw Exception.
                return false;
            }

            query = signUpQuery.replaceAll("%email", email).replaceAll("%password", pass);
            statement.executeUpdate(query);

        } catch (Exception e) {
            // TODO: Logger.
            e.printStackTrace(out);
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO: Logger.
                }
            }
        }
        return true;
    }

}