
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.Map;

/**
 * @author Vladislav Romanov
 */

public class NewEvent implements Method {

    private static String queryUsers = "SELECT email, notify_all FROM Users WHERE id IN (SELECT `user`  FROM `Controllers` WHERE `privateKey` LIKE '%key'); ";
    private static String query = "INSERT INTO `Events` (`privateKey`, `msg`, `priority`) VALUES ('%id', '%msg', '%priority')";
    // TODO SQL Injection protect.

    @SuppressWarnings("unchecked")
    public String run(Map<String, String[]> args) {

        String[] keyArr      = args.get("key");
        String[] msgArr      = args.get("msg");
        String[] priorityArr = args.get("priority");

        if (keyArr == null || msgArr == null || priorityArr == null) {
            return (new NotEnoughArguments()).toString();
        }

        String key      = keyArr[0];
        String msg      = msgArr[0];
        int priority = 0;
        try {
        	priority = Integer.parseInt(priorityArr[0]);
        } catch (Exception e) {
        	// TODO Logger.
        }

        try {
            
            {
                Statement statement = Database.createStatement();
                String q = query.replaceAll("%id", key).replaceAll("%msg", msg).replaceAll("%priority", priority + "");
                statement.executeUpdate(q);
            }

            Statement statement = Database.createStatement();
            String q = queryUsers.replaceAll("%key", key);
            ResultSet result = statement.executeQuery(q);

            result.beforeFirst();  
            result.last();  
            int size = result.getRow();
            result.beforeFirst();

            String[] emails = new String[size];
            boolean[] notifyAll = new boolean[size];

            for (int i = 0; i < size; i++) {
                result.next();
                emails[i] = result.getString("email");
                notifyAll[i] = result.getInt("notify_all") != 0;
            }
            
            Email.send(emails, "Notification", msg, notifyAll, priority != 0);

            JSONObject item = new JSONObject();
            item.put("success", "true");
            return item.toString();

        } catch (Exception e) {
            // TODO: Logger.

            JSONObject error = new JSONObject();
            error.put("message", e.getMessage());
            error.put("type", e.getClass().getSimpleName());

            JSONObject obj = new JSONObject();
            obj.put("error", error);

            return obj.toString();
        }
    }

}