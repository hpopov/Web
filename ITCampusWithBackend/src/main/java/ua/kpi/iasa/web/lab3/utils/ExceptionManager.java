package ua.kpi.iasa.web.lab3.utils;

public interface ExceptionManager<T, R> {

    default <E extends Exception> R executeExceptionableLogic(ExceptionableLogic<R, E> exceptionableLogic,
            T context) {
        try {
            return exceptionableLogic.execute();
        } catch (Exception e) {
            return handleException(e, context);
        }
    }

    R handleException(Exception exception, T context);
}
