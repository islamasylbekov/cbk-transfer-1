package kg.cbk.exception;

public class CrudOperationException extends RuntimeException {

    private static final long serialVersionUID = -3172307576389905072L;

    public CrudOperationException(String message) {
        super(message);
    }

}
