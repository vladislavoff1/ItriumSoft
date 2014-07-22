
/**
 * @author Vladislav Romanov
 */

class GetKey extends Command {
 
    public void setArg(String[] args) {
        
    }

    @Override
    public void run(ControllerState state) {
        System.out.println("Key: " + state.id + ".");
    }

}