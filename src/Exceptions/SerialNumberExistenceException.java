package Exceptions;

public class SerialNumberExistenceException extends Exception {

	public final int exceptionCode = 5;

	public SerialNumberExistenceException() {
		super("Serial number already exist");
	}

	public SerialNumberExistenceException(String message) {
		super(message);
	}
}