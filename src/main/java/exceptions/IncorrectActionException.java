package exceptions;

public class IncorrectActionException extends Exception {

    public IncorrectActionException() {
        super();
    }

    public IncorrectActionException(String errorMessage) {
        super(errorMessage);
    }
}
