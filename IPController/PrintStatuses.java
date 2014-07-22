
/**
 * @author Vladislav Romanov
 */

class PrintStatuses extends Command {
 
    public void setArg(String[] args) {
        
    }

    @Override
    public void run(ControllerState state) {
        Status[] values = Status.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i].toString().toLowerCase());
        }
    }

}