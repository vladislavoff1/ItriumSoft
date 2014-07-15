import java.util.Random;

/**
 * @author Vladislav Romanov
 */

class Generate {

    public static String id(int length, String chars) {
        Random random = new Random();
        String res = "";
        for (int i = 0; i < length; i++) {
            res += chars.charAt(Math.abs(random.nextInt()) % chars.length());
        }
        return res;
    }

}