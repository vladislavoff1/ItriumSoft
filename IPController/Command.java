
/**
 * @author Vladislav Romanov
 */

abstract class Command {

    public abstract void setArg(String[] arg) throws NotEnoughArguments;

    public void run(ControllerState state) {
        System.out.println("Class " + this.getClass().getName() + " runs.");
    }

}