
/**
 * @author Vladislav Romanov
 */

class SimpleMessage {
    
    public String id;
    public String status;


    public SimpleMessage(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String toString() {
        return "(" + id + ")"  + status;
    }
}