
/**
 * @author Vladislav Romanov
 */

class MessageParser {

    public static SimpleMessage parse(String message) throws MessageParserError {

        String[] args = message.split("&");
        if (args.length != 2) {
            throw new MessageParserError("2 arguments were expected. Input: " + message);
        }

        SimpleMessage res = new SimpleMessage(args[0], args[1]);

        return res;
    }

}