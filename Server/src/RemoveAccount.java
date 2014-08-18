
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.Map;

/**
 * @author Vladislav Romanov
 */

public class RemoveAccount implements Method {

    private static String queryGetID = "SELECT * FROM  `Users` WHERE  `email` LIKE  '%email' AND  `password` LIKE  '%password'";
    private static String query = "DELETE FROM `Users` WHERE `Users`.`id` = %id";
    // TODO SQL Injection protect.

    @SuppressWarnings("unchecked")
	public String run(Map<String, String[]> args) {

        int id;
        
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

            Statement statement = Database.createStatement();
            String q = query.replaceAll("%id", id + "");
            int res = statement.executeUpdate(q);
 
            JSONObject resp = new JSONObject();
            resp.put("success", res != 0 ? true : false);
            return resp.toString();

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
