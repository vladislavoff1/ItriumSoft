import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vladislav Romanov
 */

@WebServlet("/GetState")
public class GetState extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetState() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String id = request.getParameter("id");
        String status = request.getParameter("status");

        if (id == null || status == null) {
            // TODO: Make error response.
            return;
        }
        

        SimpleMessage msg = new SimpleMessage(id, status);

        PrintWriter out = response.getWriter();

        try {
            Database.setStatus(msg.id, msg.status);
        } catch (Exception e) { //TODO: Specify the MySql's exceptions. 
            out.println(e);
            e.printStackTrace(out);
            // TODO: Make error response.
            return;
        }

        //Make response
        out.println(msg);
        out.close();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  

    }

}
