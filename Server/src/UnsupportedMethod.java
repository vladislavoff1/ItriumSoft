
public class UnsupportedMethod extends JsonException {

	private static final long serialVersionUID = 1L;
	private static final int id = 122;
	public UnsupportedMethod(String name) {
		super("Unsupported method " + name, "UnsupportedMethod", id);
	}
}
