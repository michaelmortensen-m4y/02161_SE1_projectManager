package application;
// By: Andreas T. Christensen
public class InitialsInvalidException extends Exception {
	String operation;
	
	public InitialsInvalidException (String errorMessage, String operation) {
		super(errorMessage);
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}
}
