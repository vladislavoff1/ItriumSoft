
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetEvent
 */
@WebServlet("/GetEvent")
public class GetEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String msg = request.getParameter("eventMsg");
		int priority = Integer.parseInt(request.getParameter("eventPriority"));

		PrintWriter out = response.getWriter();
		String db = null;

		try {
			out.println("Какой-то текст");
			msg = new String(msg.getBytes("UTF-8"), "ISO-8859-1");
			out.println("UTF-8  ->  ISO-8859-1  " + msg);
			db = Database.sendEvent(id, msg, priority);
			out.println(db);

			msg = new String(msg.getBytes("ISO-8859-1"), "UTF-8");
			out.println("ISO-8859-1  ->  UTF-8  " + msg);
			db = Database.sendEvent(id, msg, priority);
			out.println(db);

		} catch (Exception e) { // TODO: Specify the MySql's exceptions.
			out.println(e);
			e.printStackTrace(out);
			// TODO: Make error response.
			return;
		}

		// Make response

		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
