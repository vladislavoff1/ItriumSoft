
/**
 * @author Vladislav Romanov
 */

class ControllerState {

    public String id;
    public Status status;

    public ControllerState(Status status) {
        this.id = Generate.id(8, "0123456789ABCDEF");
        this.status = status;
    }

    public ControllerState(String id, Status status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String toString() {
        return status.name().toLowerCase();
    }
      
}