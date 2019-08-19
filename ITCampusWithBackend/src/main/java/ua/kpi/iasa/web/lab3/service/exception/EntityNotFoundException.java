package ua.kpi.iasa.web.lab3.service.exception;

@SuppressWarnings("serial")
public class EntityNotFoundException extends RuntimeException {
	
	public EntityNotFoundException(Throwable th) {
		super(th);
	}

}
