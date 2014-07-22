
import com.itrium.borey.client.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetControllers
 */
@WebServlet("/GetControllers")
public class GetControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetControllers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

        Object userObj = request.getSession().getAttribute("user");
        if (userObj == null) {
            response.getWriter().print("null");
            return;
        }
        int user = (int) userObj;

        String res = "";
        ControllerParams[] statuses = Controllers.getControllers(user, new PrintWriter(System.out));
        for(int i = 0; i < statuses.length; i++) {
            String id = statuses[i].key;
            String time = statuses[i].time;
            String status = statuses[i].status;

            res += "id" + i + "=" + id;
            res += "&";
            res += "time" + i + "=" + time;
            res += "&";
            res += "status" + i + "=" + status;
            res += "&";
        }
        response.getWriter().print(res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
