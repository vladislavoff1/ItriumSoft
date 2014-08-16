
/**
 * @author Vladislav Romanov
 */

class StartIPController {

    public static void main(String[] args) {

        ControllerState state;

        if (args.length > 0) {
            String id = args[0];
            state = new ControllerState(id, Status.NORMAL);
        } else {
            state = new ControllerState(Status.NORMAL); 
        }
        
        new ServerConnection(state);
        new CommandReader(state);
        
    }
    
}