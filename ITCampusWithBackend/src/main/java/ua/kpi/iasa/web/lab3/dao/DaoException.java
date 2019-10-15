package ua.kpi.iasa.web.lab3.dao;

@SuppressWarnings("serial")
public class DaoException extends Exception {

    public DaoException() {
        super();
    }

    public DaoException(String message, Throwable e) {
        super(message, e);
    }

    public DaoException(String message) {
        super(message);
    }
}
