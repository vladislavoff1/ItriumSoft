import java.util.Scanner;

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

            String[] args = input.split("\\s");

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