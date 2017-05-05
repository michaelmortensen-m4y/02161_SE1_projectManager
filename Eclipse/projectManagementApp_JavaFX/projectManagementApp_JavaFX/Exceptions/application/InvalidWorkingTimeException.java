package application;

public class InvalidWorkingTimeException extends Exception {
	String operation;
	
	public InvalidWorkingTimeException (String errorMessage, String operation) {
		super(errorMessage);
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}
}
