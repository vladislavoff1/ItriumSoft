
/**
 * @author Vladislav Romanov
 */

class ControllerState {

    private Status status;

    public ControllerState(Status status) {
        setStatus(status);
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