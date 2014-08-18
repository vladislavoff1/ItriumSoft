
public class NotEnoughArguments extends JsonException {

	private static final long serialVersionUID = 1L;
	private static final int id = 123;
	
	public NotEnoughArguments() {
		super("", "NotEnoughArguments", id);
	}
}
