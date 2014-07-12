
/**
 * @author Vladislav Romanov
 */

class StartIPController {

    public static void main(String[] args) {

        ControllerState state = new ControllerState(Status.NORMAL);
        new ServerConnection(state);
        new CommandReader(state);
        
    }

    
}