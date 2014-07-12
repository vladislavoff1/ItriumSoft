
/**
 * @author Vladislav Romanov
 */

class IPController {

    public static void main(String[] args) {

        if (args.length == 0) {
            PrintHelp.print();
            return;
        }

        Command command;

        try {
            command = CommandParser.parse(args);
        } catch (CommandParseError e) {
            System.out.println(e.getMessage());
            return;
        }

        command.run();

    }

    
}