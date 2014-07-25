
/**
 * @author Vladislav Romanov
 */

class NewEvent extends Command {
 
    private Event event;

    public void setArg(String[] args) {
        if (args.length < 1) {
            throw new NotEnoughArguments(getClass(), 1);
        }

        event = new Event(args[0]);

    }

    @Override
    public void run(ControllerState state) {

        String msg = "New event \"" + event + "\" was created.";
        
        new EventSender(state, event);

        System.out.println(msg);
    }

}