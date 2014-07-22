package com.itrium.borey.client;

import java.io.PrintWriter;
import java.sql.*;

/**
 * @author Vladislav Romanov
 */
//TODO: Connection pool.

public class Controllers {

    protected static String driverName = "com.mysql.jdbc.Driver";
    protected static String url = "jdbc:mysql://localhost:3306/IPControllerDB";
    protected static String name = "root";
    protected static String password = "vlad007.1996@yandex.ru";

    private static String addQuery = "INSERT INTO `IPControllerDB`.`Controllers` (`privateKey`, `user`) VALUES ('%key', '%user');";
    // TODO: SQL Injection.

    public static boolean add(String key, int user) throws Exception {

        // TODO: Key can't consist some symbols. E.g. '?', '"', ';'. 

        Connection connection = null;
        int result;
        boolean res = false;

        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();

            String query = addQuery.replaceAll("%key", key).replaceAll("%user", user + "");
            result = statement.executeUpdate(query);
            res = true;
        } catch (Exception e) {
            // TODO: Logger.
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
        return res; 
    }

    private static String getControllersQuery = "SELECT * FROM `Statuses` WHERE `id` in (SELECT `privateKey` FROM `Controllers` WHERE `user` = %user)";

    public static ControllerParams[] getControllers(int user, PrintWriter out) {

        Connection connection = null;
        ResultSet result;
        ControllerParams[] res;

        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();

            String query = getControllersQuery.replaceAll("%user", user + "");
            result = statement.executeQuery(query);

            result.beforeFirst();  
            result.last();  
            int size = result.getRow();
            res = new ControllerParams[size];
        	result.beforeFirst();
            for (int i = 0; i < size; i++) {
            	result.next();
            	res[i] = new ControllerParams();
                res[i].key = result.getString("id");
                res[i].time = result.getTimestamp("time").toString();
                res[i].status = result.getString("status");
            }

        } catch (Exception e) {
            // TODO: Logger.
        	e.printStackTrace(out);
            return null;
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

}