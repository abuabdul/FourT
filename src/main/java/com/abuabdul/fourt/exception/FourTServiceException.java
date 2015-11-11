package com.abuabdul.fourt.exception;

/**
 * Handles Service layer exceptions
 * 
 * @author abuabdul
 *
 */
public class FourTServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 234324L;

	public FourTServiceException() {

	}

	public FourTServiceException(String message) {
		super(message);
	}

	public FourTServiceException(Throwable cause) {
		super(cause);
	}

	public FourTServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public FourTServiceException(String message, Error error) {
		super(message, error);
	}

}
