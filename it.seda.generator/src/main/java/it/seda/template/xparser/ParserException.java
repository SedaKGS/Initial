package it.seda.template.xparser;


public class ParserException extends RuntimeException {

	private static final long serialVersionUID = -8774381176577556077L;

	public ParserException(String message) {
		super(message);
	}

	public ParserException(String message, Throwable cause) {
		super(message, cause);
	}

}
