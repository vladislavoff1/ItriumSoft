
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.Map;

/**
 * @author Vladislav Romanov
 */

public class RemoveController implements Method {

    private static String queryGetID = "SELECT * FROM  `Users` WHERE  `email` LIKE  '%email' AND  `password` LIKE  '%password'";
    private static String query = "DELETE FROM `Controllers` WHERE `privateKey` = '%key'";
    // TODO SQL Injection protect.

    @SuppressWarnings("unchecked")
    public String run(Map<String, String[]> args) {

        int id;
        
        String[] emailArr    = args.get("email");
        String[] passwordArr = args.get("password");
        String[] keyArr      = args.get("key");

        if (emailArr == null || passwordArr == null || keyArr == null) {
            return (new NotEnoughArguments()).toString();
        }

        String email    = emailArr[0];
        String password = passwordArr[0];
        String key      = keyArr[0];

        try {
            
            {
                Statement statement = Database.createStatement();
                String q = queryGetID.replaceAll("%email", email).replaceAll("%password", password);
                ResultSet result = statement.executeQuery(q);
     
                id = result.next() ? result.getInt("id") : -1;
            }

            Statement statement = Database.createStatement();
            String q = query.replaceAll("%id", key);
            statement.executeUpdate(q);

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