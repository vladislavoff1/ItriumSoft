
/**
 * @author Vladislav Romanov
 */

class UnsupportedCommand extends CommandParseError {

    public UnsupportedCommand(String name) {
        super("Can't understand command \"" + name + "\".");
    }

}