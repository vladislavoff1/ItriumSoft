import java.sql.SQLException;
import java.util.Map;

public interface Method {
    public String run(Map<String, String[]> args);
}