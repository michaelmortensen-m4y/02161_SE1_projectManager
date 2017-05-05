package application;

public class NoSuchUserException extends Exception {
	String operation;
	
	public NoSuchUserException (String errorMessage, String operation) {
		super(errorMessage);
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}
}
