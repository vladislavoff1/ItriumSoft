import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Vladislav Romanov
 */

class CommandReader implements Runnable {

    private ControllerState state;

    public CommandReader(ControllerState state) {
        this.state = state;
        new Thread(this, "CommandReader").start();
    }

    public void run() {

        while (true) {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("admin@IPController:~$ ");
            String input = keyboard.nextLine();

            boolean quote = false;
            boolean space = true;
            int i = 0;

            List<String> list = new ArrayList<String>();

            while (input.length() > 0) {

                if (i >= input.length()) {
                    list.add(input);
                    break;
                }

                if (!quote && input.charAt(i) == ' ') {
                    if (!space) {
                        list.add(input.substring(0, i));
                    }
                    input = input.substring(i + 1);
                    i = 0;
                    space = true;
                } else {
                    space = false;
                }

                if (input.charAt(i) == '"') {
                    if (quote) {
                        list.add(input.substring(0, i));
                    }
                    input = input.substring(i + 1);
                    i = 0;
                    quote = !quote;
                }

                i++;
            }
            String[] args = new String[list.size()];
            args = list.toArray(args); // = input.split("\\s");

            Command command;

            try {
                command = CommandParser.parse(args);
            } catch (CommandParseError e) {
                System.out.println(e.getMessage());
                continue;
            }

            command.run(state);
        }
    }

    
}