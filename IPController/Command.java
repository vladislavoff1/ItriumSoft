
/**
 * @author Vladislav Romanov
 */

abstract class Command {

    public abstract void setArg(String[] arg) throws NotEnoughArguments;

    public void run() {
        System.out.println("Class " + this.getClass().getName() + " runs.");
    }

}