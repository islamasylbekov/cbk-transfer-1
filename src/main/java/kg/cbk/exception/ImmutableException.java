package kg.cbk.exception;

public class ImmutableException extends RuntimeException {

    private static final long serialVersionUID = -3172307576389905072L;

    public ImmutableException() {
        super("Сущность неподлежит созданию/изменению/удалению");
    }

    public ImmutableException(String message) {
        super(message);
    }

}
