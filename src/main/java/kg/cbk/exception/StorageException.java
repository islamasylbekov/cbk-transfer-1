package kg.cbk.exception;

public class StorageException extends RuntimeException {

    private static final long serialVersionUID = -1697721879159516613L;

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
