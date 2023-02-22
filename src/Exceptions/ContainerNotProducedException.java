package Exceptions;

public class ContainerNotProducedException extends Exception {

	public final int exceptionCode = 11;

	public ContainerNotProducedException() {
		super("The container is not yet created.");
	}

	public ContainerNotProducedException(String message) {
		super(message);
	}
}