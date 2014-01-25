package it.seda.security.service.exceptions;

@SuppressWarnings("serial")
public class DuplicateAccountException extends RuntimeException {

	public DuplicateAccountException() {
		// TODO Auto-generated constructor stub
	}

	public DuplicateAccountException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DuplicateAccountException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public DuplicateAccountException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
