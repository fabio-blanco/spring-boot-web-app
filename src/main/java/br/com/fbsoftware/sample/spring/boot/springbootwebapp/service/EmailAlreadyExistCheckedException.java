package br.com.fbsoftware.sample.spring.boot.springbootwebapp.service;

/**
 * Exception that indicates that an email already exists in the User table.
 *
 * @author Fabio M. Blanco
 * @since 16/04/2021
 */
public class EmailAlreadyExistCheckedException extends ServiceCheckedException {

	private static final long serialVersionUID = -7175858625257939742L;

	public EmailAlreadyExistCheckedException() {
	}

	public EmailAlreadyExistCheckedException(String message) {
		super(message);
	}

	public EmailAlreadyExistCheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailAlreadyExistCheckedException(Throwable cause) {
		super(cause);
	}

	public EmailAlreadyExistCheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
