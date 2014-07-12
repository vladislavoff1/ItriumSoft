
/**
 * @author Vladislav Romanov
 */

class ServerConnection implements Runnable {

    private static int deltaTime = 10000;
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
        }
    }

    public void sendToServer() {
        //TODO: Send ControllerState to the Server.
    }
      
}