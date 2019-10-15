package ua.kpi.iasa.web.lab3.utils;

@FunctionalInterface
public interface ExceptionableLogic<R, E extends Exception> {
    R execute() throws E;
}
