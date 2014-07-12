
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
        state.setStatus(status);
        System.out.println("Status was changed to " + status.name().toLowerCase() + ".");
    }

}