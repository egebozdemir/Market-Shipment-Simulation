package Exceptions;

public class ContainerShippedException extends Exception {

	public final int exceptionCode = 7;

	public ContainerShippedException() {
		super("Container shipped.");
	}

	public ContainerShippedException(String message) {
		super(message);
	}
}