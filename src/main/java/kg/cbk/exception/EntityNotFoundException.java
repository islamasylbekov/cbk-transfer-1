package kg.cbk.exception;

public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -7572575714179705067L;

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

}
