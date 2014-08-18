
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.Map;

/**
 * @author Vladislav Romanov
 */

public class SignIn implements Method {

    private static String query = "SELECT * FROM  `Users` WHERE  `email` LIKE  '%email' AND  `password` LIKE  '%password'";
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
        	
        	Statement statement = Database.createStatement();
            String q = query.replaceAll("%email", email).replaceAll("%password", password);
            ResultSet result = statement.executeQuery(q);
 
            id = result.next() ? result.getInt("id") : -1;
        } catch (Exception e) {
            // TODO: Logger.

            JSONObject error = new JSONObject();
            error.put("message", e.getMessage());
            error.put("type", e.getClass().getSimpleName());

            JSONObject obj = new JSONObject();
            obj.put("error", error);

//            return obj.toString();
            String err = "";
            StackTraceElement[] st = e.getStackTrace();
            for (int i = 0; i < st.length; i++){
            	err += st[i].toString() + "\n";
            }
            return err;
        }
        
        JSONObject response = new JSONObject();
        if (id >= 0) {
        	response.put("id", id);
        } else {
            response.put("id", "undefined");
        }

        return response.toString();
    }

}
