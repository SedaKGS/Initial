package it.seda.sem.mvc.manager.exceptions;

@SuppressWarnings("serial")
public class DummyException extends RuntimeException {

	public DummyException(String message) {
		super(message);
	}

	public DummyException(Throwable cause) {
		super(cause);
	}

	public DummyException(String message, Throwable cause) {
		super(message, cause);
	}

}
