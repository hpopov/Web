package ua.kpi.iasa.web.lab3.exception;

@SuppressWarnings("serial")
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("The access was denied");
    }
}
