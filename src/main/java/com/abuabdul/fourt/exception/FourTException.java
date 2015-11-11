package com.abuabdul.fourt.exception;

/**
 * An Application level exception, captures exception message and confine it as
 * RuntimeException so application can handle the exception message delicately
 * using exception resolvers
 * 
 * @author abuabdul
 *
 */
public class FourTException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 23434324L;

	public FourTException() {

	}

	public FourTException(String message) {
		super(message);
	}

	public FourTException(Throwable cause) {
		super(cause);
	}

	public FourTException(String message, Throwable cause) {
		super(message, cause);
	}

	public FourTException(String message, Error error) {
		super(message, error);
	}

}
