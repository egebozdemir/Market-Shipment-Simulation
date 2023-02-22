package Exceptions;

public class VolumeOverflowException extends Exception {

	public final int exceptionCode = 2;

	public VolumeOverflowException() {
		super("Volume been overflowed");
	}

	public VolumeOverflowException(String message) {
		super(message);
	}
}