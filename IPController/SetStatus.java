
/**
 * @author Vladislav Romanov
 */

class SetStatus extends Command {
 
    private Status status;

    public void setArg(String[] args) {
        if (args.length < 1) {
            throw new NotEnoughArguments(getClass(), 1);
        }
        try {
            this.status = Status.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new BadArgument(getClass(), args[0]);
        }

    }

    @Override
    public void run(ControllerState state) {

        String msg = "Status was changed to " + status.name().toLowerCase() + ".";
        
        if (state.status.equals(status)) {
            System.out.println(msg);
            return; 
        }

        state.setStatus(status);

        Event event = new Event(msg);
        new EventSender(state, event);

        System.out.println(msg);
    }

}