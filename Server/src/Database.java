
import java.sql.*;
import java.util.Properties;

/**
 * @author Vladislav Romanov
 */

public class Database {

    protected static String driverName = "com.mysql.jdbc.Driver";
    protected static String url        = "jdbc:mysql://127.10.62.130:3306/serverborey";
    protected static String name       = "adminNILRcWH";
    protected static String password   = "Nw-zHuk5VKZN";

    protected static Connection connection = null;

    static Statement createStatement() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return connection.createStatement();
        }
        Class.forName(driverName);
        Properties connInfo = new Properties();
        connInfo.put("user", name);
        connInfo.put("password", password);

        connInfo.put("useUnicode","true");
        connInfo.put("charSet", "windows-1251");
        connInfo.put("characterEncoding", "windows-1251");

        Connection connection = DriverManager.getConnection(url, connInfo);
        return connection.createStatement();
    }

    private static String getStatusQuery = "INSERT INTO `Statuses`(`id`, `status`) VALUES (\"%id\", \"%status\") ON DUPLICATE KEY UPDATE `time` = now(), `status` = \"%status\";";

    public static void setStatus(String id, String status) throws Exception {
        Connection connection = null;
        //int result;

        //try {
            Statement statement = createStatement();

            String query = getStatusQuery.replaceAll("%id", id).replaceAll("%status", status);
            statement.executeUpdate(query);
        /*} catch (Exception ex) {
            //TODO Logger
        } finally {11
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



    private static String sendEventQuery = "INSERT INTO `Events` (`privateKey`, `msg`, `priority`) VALUES ('%id', '%msg', '%priority')";

    public static String sendEvent(String id, String msg, int priority) throws Exception {

        Properties connInfo = new Properties();
        connInfo.put("user", name);
        connInfo.put("password", password);

        connInfo.put("useUnicode","true");
        connInfo.put("charSet", "utf-8");
        connInfo.put("characterEncoding", "utf-8");

        Connection connection = DriverManager.getConnection(url, connInfo);
        Statement statement = connection.createStatement();
        
        String query = sendEventQuery.replaceAll("%id", id).replaceAll("%msg", msg).replaceAll("%priority", priority + "");
        statement.executeUpdate(query);

        return query;
    }

}