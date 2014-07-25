import java.net.*;
import java.io.*;

/**
 * @author Vladislav Romanov
 */

class ServerConnection implements Runnable {

    private final static String targetURL = "http://localhost:8080/Server/GetState?id=%id&status=%status";

    private static int deltaTime = 1000;
    private ControllerState controllerState;

    public ServerConnection(ControllerState controllerState) {
        this.controllerState = controllerState;
        new Thread(this, "ServerConnection").start();
    }


    public void run() {
        while (true) {
            sendToServer();
            try {
                Thread.sleep(deltaTime);
            } catch (InterruptedException e) {
                System.out.println("IPController stopped.");
                return;
            }
            // TODO: Catch other errors.
        }
    }

    public void sendToServer() {
        // TODO: Send ControllerState to the Server.
        String target = targetURL.replaceAll("%id", controllerState.id).replaceAll("%status", controllerState.status.toString());

        try {
            URL server = new URL(target);
            URLConnection yc = server.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            /*String inputLine;
            while ((inputLine = in.readLine()) != null) 
                System.out.println(inputLine);*/
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Logger
            return;
        }
    }
}
