
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/Update", loadOnStartup=1)
public class Update extends HttpServlet implements Runnable {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
    }

    public void init() {
        new Thread(this, "Update").start();
    }

    String query = "UPDATE `Statuses` SET `status`='ALARM' WHERE `time` < NOW() - INTERVAL 1 MINUTE;";

    public void run() {
        while (true) {
            try {
                Thread.sleep(15000);
                Database.createStatement().executeUpdate(query);
            } catch (Exception e) {
                // TODO Logger;
            }
        }
    }
    
}