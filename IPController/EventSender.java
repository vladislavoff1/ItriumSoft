import java.net.*;
import java.io.*;

/**
 * @author Vladislav Romanov
 */


class EventSender implements Runnable  {
    
    private final static String targetURL = "http://serverborey-vladislavoff1.rhcloud.com/GetEvent?id=%id&eventMsg=%eventMsg&eventPriority=%eventPriority";

    private Event event;
    private ControllerState controllerState;

    public EventSender(ControllerState controllerState, Event event) {
        this.controllerState = controllerState;
        this.event = event;
        new Thread(this, "EventSender").start();
    }

    public void run() {
        sendToServer();
    }

    public void sendToServer() {
        try {
            String target = targetURL.replaceAll("%id", controllerState.id).replaceAll("%eventMsg", URLEncoder.encode(event.msg, "UTF-8")).replaceAll("%eventPriority", event.priority + "").replaceAll(" ", "%20");
            System.out.println(target);
            URL server = new URL(target);
            URLConnection yc = server.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: Logger
            return;
        }
    }

}