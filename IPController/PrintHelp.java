
/**
 * @author Vladislav Romanov
 */

class PrintHelp {

    private static String help = "Usage: java IPController command                                         " + "\n" +
                                 "where command include:                                                   " + "\n" +
                                 "    SetStatus <normal|disarm|break|alarm|part>        changes current status";

    public static void print() {
        System.out.println(help);
    }

}