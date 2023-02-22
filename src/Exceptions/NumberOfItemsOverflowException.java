package Exceptions;

public class NumberOfItemsOverflowException extends Exception {

	public final int exceptionCode = 6;

	public NumberOfItemsOverflowException() {
		super("Number of items been overflowed");
	}

	public NumberOfItemsOverflowException(String message) {
		super(message);
	}
}