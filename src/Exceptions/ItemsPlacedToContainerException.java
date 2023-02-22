package Exceptions;

public class ItemsPlacedToContainerException extends Exception {

	public final int exceptionCode = 8;

	public ItemsPlacedToContainerException() {
		super("Items cannot be placed to a container directly.");
	}

	public ItemsPlacedToContainerException(String message) {
		super(message);
	}
}