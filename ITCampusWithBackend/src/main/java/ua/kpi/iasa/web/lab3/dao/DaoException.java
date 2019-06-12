package ua.kpi.iasa.web.lab3.dao;

@SuppressWarnings("serial")
public class DaoException extends Exception {

	public DaoException() {
		super();
	}
	
	public DaoException(String message, Exception cause) {
		super(message, cause);
	}
}
