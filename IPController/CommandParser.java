import java.util.Arrays;

/**
 * @author Vladislav Romanov
 */

class CommandParser {

    public static Command parse(String[] args) throws CommandParseError {

        if (args.length == 0) {
            throw new CommandParseError("No command.");
        }


        String commandName = "";
        String commandInput = args[0];
        char[] commandNameChar = commandInput.toCharArray();

        int wordLength = 0;
        int delay = 0;
        for (int i = 0; i < commandNameChar.length; i++) {
            
            if (commandNameChar[i] == '-' && wordLength != 0) {
                wordLength = 0;
                continue;
            }

            if (wordLength == 0) {
                commandNameChar[i] = Character.toUpperCase(commandNameChar[i]);
            }

            commandName += commandNameChar[i];
            wordLength++;
        }

        Command command;

        try {

            command = (Command)Class.forName(commandName).newInstance();
            command.setArg(Arrays.copyOfRange(args, 1, args.length));

        } catch (ReflectiveOperationException e) {

            throw new UnsupportedCommand(commandName);

        }

        return command;
    }
}