public class MethodParser {

    public static Method parse(String name) throws UnsupportedMethod {
        Method method;

        try {
            method = (Method)Class.forName(name).newInstance();
        } catch (ReflectiveOperationException e) {
            throw new UnsupportedMethod(name);
        }

        return method;
    }   

}