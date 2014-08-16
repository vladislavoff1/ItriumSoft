import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Vladislav Romanov
 */

@WebServlet("/signup")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (email == null || password == null) {
			JSONObject resp = new JSONObject();
			resp.put("success", false);
			response.getWriter().print(resp.toString());
			return;
		}
		String idStr = signup(email, password);
		Integer id = null;

		if (!idStr.equals("undefined")) {
			id = Integer.parseInt(idStr);
		}

		if (id != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", id);
			session.setAttribute("email", email);
			session.setAttribute("password", password);
		}
		/*
		 * Cookie cookie = new Cookie("user", email);
		 * response.addCookie(cookie);
		 */

		JSONObject resp = new JSONObject();
		resp.put("success", id != null ? true : false);
		response.getWriter().print(resp.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private final static String targetURL = "http://serverborey-vladislavoff1.rhcloud.com/api?method=SignUp&email=%email&password=%password";

	private String signup(String email, String password) {
		String target = targetURL.replaceAll("%email", email).replaceAll(
				"%password", password);

		JSONParser jsonParser = new JSONParser();
		try {
			URL server = new URL(target);
			URLConnection yc = server.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));

			String response = "";
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response += inputLine + "\n";
			}
			in.close();

			Object obj = jsonParser.parse(response);

			JSONObject jsonObject = (JSONObject) obj;
			String id = jsonObject.get("id").toString();

			return id;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: Logger
			return e.toString();
		}
	}

}
