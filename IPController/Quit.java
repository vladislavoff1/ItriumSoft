
/**
 * @author Vladislav Romanov
 */

class Quit extends Command {
 
    public void setArg(String[] args) {
        
    }

    @Override
    public void run(ControllerState state) {
        System.out.println("IPController is stopped.");
        System.exit(0);
    }

}