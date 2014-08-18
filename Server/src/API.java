 
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodStr = request.getParameter("method");
        if (methodStr == null) {
            return;
        }
        Method method = null;
        try {
            method = MethodParser.parse(methodStr);
        } catch (UnsupportedMethod e) {
            response.getWriter().print(e);
            return;
        }

        String resp = null;
        
        resp = method.run(request.getParameterMap());

        if (resp != null) {
            response.getWriter().print(resp);
            return;
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
