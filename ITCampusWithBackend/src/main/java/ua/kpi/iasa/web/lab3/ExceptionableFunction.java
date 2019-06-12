package ua.kpi.iasa.web.lab3;

public interface ExceptionableFunction<T,R,E extends Exception> {
	R apply(T arg) throws E;
}
