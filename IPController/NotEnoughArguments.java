
/**
 * @author Vladislav Romanov
 */

class NotEnoughArguments extends CommandParseError {

    public NotEnoughArguments(Class className, int argsCount) {
        super("Command \"" + className.getName() + "\" requires " + argsCount + " arguments.");
    }

}