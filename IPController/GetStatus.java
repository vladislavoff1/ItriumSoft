
/**
 * @author Vladislav Romanov
 */

class GetStatus extends Command {
 
    public void setArg(String[] args) {
        
    }

    @Override
    public void run(ControllerState state) {
        System.out.println("Status: " + state.getStatus().name().toLowerCase() + ".");
    }

}