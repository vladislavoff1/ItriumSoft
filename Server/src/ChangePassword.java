
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.Map;

/**
 * @author Vladislav Romanov
 */

public class ChangePassword implements Method {

    private static String queryGetID = "UPDATE `Users` SET `password` = '%new_password' WHERE  `email` LIKE  '%email' AND  `password` LIKE  '%password'";
    // TODO SQL Injection protect.

    @SuppressWarnings("unchecked")
    public String run(Map<String, String[]> args) {

        int id;
        
        String[] emailArr    = args.get("email");
        String[] passwordArr = args.get("password");
        String[] newPasswordArr = args.get("new_password");

        if (emailArr == null || passwordArr == null || newPasswordArr == null) {
            return (new NotEnoughArguments()).toString();
        }
        
        String email    = emailArr[0];
        String password = passwordArr[0];
        String newPassword = newPasswordArr[0];

        try { 
            
            Statement statement = Database.createStatement();
            String q = queryGetID.replaceAll("%email", email).replaceAll("%password", password).replaceAll("%new_password", newPassword);
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
