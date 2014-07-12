
/**
 * @author Vladislav Romanov
 */

class BadArgument extends CommandParseError {

    public BadArgument(Class className, String arg) {
        super("Bad argument \"" + arg + "\" for command \"" + className.getName() + "\".");
    }

}