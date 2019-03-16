package assignment.exception;

public class FraudTransException extends Exception {

	private static final long serialVersionUID = 3134136663099887367L;

	public FraudTransException(final String msg) {
		super(msg);
	}

	public FraudTransException(final Throwable cause) {
		super(cause);
	}

	public FraudTransException(final String msg, final Throwable cause) {
		super(msg, cause);
	}
}
