
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.Map;

/**
 * @author Vladislav Romanov
 */

public class ClearHystory implements Method {

    private static String queryGetID = "SELECT * FROM  `Users` WHERE  `email` LIKE  '%email' AND  `password` LIKE  '%password'";
    private static String queryGetControllers = "SELECT * FROM `Statuses` WHERE `id` in (SELECT `privateKey` FROM `Controllers` WHERE `user` = %user)";
    private static String query = "DELETE FROM `Events` WHERE %find";
    // TODO SQL Injection protect.

    @SuppressWarnings("unchecked")
    public String run(Map<String, String[]> args) {

        int id;
        String find = "";
        
        String[] emailArr    = args.get("email");
        String[] passwordArr = args.get("password");

        if (emailArr == null || passwordArr == null) {
            return (new NotEnoughArguments()).toString();
        }

        String email    = emailArr[0];
        String password = passwordArr[0];

        try { 

            
            {
                Statement statement = Database.createStatement();
                String q = queryGetID.replaceAll("%email", email).replaceAll("%password", password);
                ResultSet result = statement.executeQuery(q);
     
                id = result.next() ? result.getInt("id") : -1;
            }

            { 
                Statement statement = Database.createStatement();
                String q = queryGetControllers.replaceAll("%user", id + "");
                ResultSet result = statement.executeQuery(q);
                
                result.beforeFirst();  
                result.last();  
                int size = result.getRow();
                result.beforeFirst();

                for (int i = 0; i < size; i++) {
                    result.next();

                    if (i != 0) {
                        find += " OR ";
                    }

                    find += "`privateKey` LIKE '" + result.getString("id") + "'";
                }
            }

            Statement statement = Database.createStatement();
            String q = query.replaceAll("%find", find);
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
