
/**
 * @author Vladislav Romanov
 */


class Event {
    
    public String msg;
    public int priority;

    public Event(String msg) {
        this.msg = msg;
        this.priority = 0;
    }

    public Event(String msg, int priority) {
        this.msg = msg;
        this.priority = priority;
    }

    public String toString() {
        return msg;
    }
    
}