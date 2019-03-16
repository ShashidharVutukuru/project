package assignment.exception;

public class InvalidInputException extends FraudTransException {

	private static final long serialVersionUID = 5314727363657369988L;

	public InvalidInputException(final String msg) {
		super(msg);
	}

	public InvalidInputException(final String msg, final Throwable cause) {
		super(msg, cause);
	}
}
