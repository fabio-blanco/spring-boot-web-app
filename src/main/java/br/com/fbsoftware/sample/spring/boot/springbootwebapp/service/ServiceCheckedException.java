package br.com.fbsoftware.sample.spring.boot.springbootwebapp.service;

/**
 * General service checked exception.
 *
 * @author Fabio M. Blanco
 * @since 16/04/2021
 */
public class ServiceCheckedException extends Exception {

	private static final long serialVersionUID = 4055145655734316467L;

	public ServiceCheckedException() {
	}

	public ServiceCheckedException(String message) {
		super(message);
	}

	public ServiceCheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceCheckedException(Throwable cause) {
		super(cause);
	}

	public ServiceCheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
