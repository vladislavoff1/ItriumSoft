import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.itrium.borey.client.Authorization;;

/**
 * @author Vladislav Romanov
 */


@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private static String successLink = "status.jsp";
    private static String failedLink = "index.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        int id = Authorization.signIn(email, password);
        String link = id != -1 ? successLink : failedLink;
        
        HttpSession session = request.getSession();
        session.setAttribute("user", id);
        session.setAttribute("email", email);
        /*Cookie cookie = new Cookie("user", email);
        response.addCookie(cookie);*/

        response.sendRedirect(link);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
