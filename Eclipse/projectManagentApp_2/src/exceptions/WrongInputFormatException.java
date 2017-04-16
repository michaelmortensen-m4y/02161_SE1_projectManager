package exceptions;

public class WrongInputFormatException extends Exception {
	private static final long serialVersionUID = 1L;

	public WrongInputFormatException(String string) {
		super(string);
	}

}
