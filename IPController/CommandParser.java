import java.util.Arrays;

/**
 * @author Vladislav Romanov
 */

class CommandParser {

    public static Command parse(String[] args) throws CommandParseError {

        Command command;

        try {

            command = (Command)Class.forName(args[0]).newInstance();
            command.setArg(Arrays.copyOfRange(args, 1, args.length));

        } catch (ReflectiveOperationException e) {

            throw new UnsupportedCommand(args[0]);

        }

        return command;
    }
}