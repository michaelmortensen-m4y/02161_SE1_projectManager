package application;
// By: Michael Mortensen
public class AlreadyJoinedWorkActivity extends Exception {
	String operation;
	
	public AlreadyJoinedWorkActivity (String errorMessage, String operation) {
		super(errorMessage);
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}
}
