package exceptions;

public class IncorrectDataException extends Exception {

    public IncorrectDataException() {
        super();
    }

    public IncorrectDataException(String errorMessage) {
        super(errorMessage);
    }
}
