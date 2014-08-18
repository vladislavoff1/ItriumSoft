
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class API
 */
@WebServlet("/api")
public class API extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public API() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private final static String[] importantMethods = new String[]{"ChangeEmail", "ChangePassword", "RemoveAccount"};

    private final static String targetURL = "http://serverborey-vladislavoff1.rhcloud.com/api";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getQueryString() == null) {
			return;
		}

        int important = -1;
        for (int i = 0; i < importantMethods.length; i++) {
            if (importantMethods[i].equals(request.getParameter("method"))) {
                important = i;
                break;
            }
        }

        HttpSession session = request.getSession();
        String email    = (String) session.getAttribute("email");
        String password = (String) session.getAttribute("password");
		
		String target = targetURL + "?" + request.getQueryString() + "&email=" + email;

        if (important == -1) {
            target += "&password=" + password;
        }
        
        URL server = new URL(target);
        URLConnection yc = server.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        
        String resp = "";
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if (resp != "") {
                resp += "\n";
            }
            resp += inputLine;
        }
        in.close();

        response.getWriter().print(resp);
        
        if (important == -1) {
        	return;
        }
        
        Boolean success = null;
        
		try {
	        JSONParser jsonParser = new JSONParser();
	        
			Object obj = jsonParser.parse(resp);
			JSONObject jsonObject = (JSONObject) obj;
			success = (Boolean) jsonObject.get("success");
	        
		} catch (Exception e) {
			// Bad json.
			return;
		}

        if (!success || success == null) {
        	return;
        }
        
        switch (important) {
        	case 0:
        		// Change email.
        		session.setAttribute("email", request.getParameter("new_email"));
        		break;
        	case 1:
        		// Change password.
        		session.setAttribute("password", request.getParameter("new_password"));
        		break;
        	case 2:
        		// Remove account.
        		break;
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
