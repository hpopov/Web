package ua.kpi.iasa.web.lab3.service.exception;

@SuppressWarnings("serial")
public class UnableToMakeEntityException extends RuntimeException {

	public UnableToMakeEntityException() {
		super();
	}

	public UnableToMakeEntityException(String message, Throwable ex) {
		super(message, ex);
	}

	public UnableToMakeEntityException(String message) {
		super(message);
	}

}
