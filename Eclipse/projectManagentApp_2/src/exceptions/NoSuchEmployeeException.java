package exceptions;

public class NoSuchEmployeeException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoSuchEmployeeException(String string) {
		super(string);
	}
}
