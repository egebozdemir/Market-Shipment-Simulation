package Exceptions;

public class BoxNotProducedException extends Exception {

	public final int exceptionCode = 10;

	public BoxNotProducedException() {
		super("The box is not yet created.");
	}

	public BoxNotProducedException(String message) {
		super(message);
	}
}