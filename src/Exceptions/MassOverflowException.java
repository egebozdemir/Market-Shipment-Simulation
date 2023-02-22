package Exceptions;

public class MassOverflowException extends Exception {

	public final int exceptionCode = 1;

	public MassOverflowException() {
		super("Mass been overflowed");
	}

	public MassOverflowException(String message) {
		super(message);
	}
}