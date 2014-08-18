
import org.json.simple.JSONObject;

public class JsonException extends Exception {
	
	private static final long serialVersionUID = 1L;

    private String msg;
    private String name;
    private int id;

	public JsonException(String msg, String name, int id) {
		super(msg);
        this.msg  = msg;
        this.name = name;
        this.id   = id;
	}

    @SuppressWarnings("unchecked")
	public String toString() {

        JSONObject error = new JSONObject();
        error.put("message", msg);
        error.put("type", name);
        error.put("code", id);

        JSONObject obj = new JSONObject();
        obj.put("error", error);

        return obj.toString();
    }
	
}