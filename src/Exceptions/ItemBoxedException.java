package Exceptions;

public class ItemBoxedException extends Exception {

	public final int exceptionCode = 9;

	public ItemBoxedException() {
		super("The item is already boxed.");
	}

	public ItemBoxedException(String message) {
		super(message);
	}
}