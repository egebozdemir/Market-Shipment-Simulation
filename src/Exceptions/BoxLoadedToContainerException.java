package Exceptions;

public class BoxLoadedToContainerException extends Exception {

	public final int exceptionCode = 4;

	public BoxLoadedToContainerException() {
		super("Box loaded to container");
	}

	public BoxLoadedToContainerException(String message) {
		super(message);
	}
}