package Exceptions;

public class WrongBoxException extends Exception {

	public final int exceptionCode = 3;

	public WrongBoxException() {
		super("Wrong box type");
	}

	public WrongBoxException(String message) {
		super(message);
	}
}