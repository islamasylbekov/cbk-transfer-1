package kg.cbk.exception;

public class NotImplementedException extends RuntimeException {
    private static final long serialVersionUID = -5852770240725910673L;

    public NotImplementedException() {
    }

    public NotImplementedException(String message) {
        super(message);
    }
}
