import java.sql.*;

/**
 * @author Vladislav Romanov
 */

public class Database {

    protected static String driverName = "com.mysql.jdbc.Driver";
    protected static String url = "jdbc:mysql://localhost:3306/IPControllerDB";
    protected static String name = "root";
    protected static String password = "vlad007.1996@yandex.ru";


    private static String getStatusQuery = "INSERT INTO `Statuses`(`id`, `status`) VALUES (\"%id\", \"%status\") ON DUPLICATE KEY UPDATE `status` = \"%status\";";

    public static void setStatus(String id, String status) throws Exception {
        Connection connection = null;
        int result;

        //try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();

            String query = getStatusQuery.replaceAll("%id", id).replaceAll("%status", status);
            result = statement.executeUpdate(query);
        /*} catch (Exception ex) {
            //TODO Logger
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    //TODO Logger
                }
            }
        }*/

    }

    private static String setStatusQuery = "SELECT `status` FROM `Statuses` WHERE `id` = \"%id\"";

    public static String getStatus(String id) {
        Connection connection = null;
        ResultSet result;
        String res = null;

        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();

            String query = setStatusQuery.replaceAll("%id", id);
            result = statement.executeQuery(query);

            res = result.getString("status");

        } catch (Exception ex) {
            //TODO Logger
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    //TODO Logger
                }
            }
        }

        return res;
    }

}