package ua.kpi.iasa.web.lab3.exception;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import ua.kpi.iasa.web.lab3.data.ApiExceptionData;
import ua.kpi.iasa.web.lab3.data.ApiSubExceptionData;
import ua.kpi.iasa.web.lab3.security.InvalidJwtAuthenticationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        final String error = "Malformed JSON request";
        return buildResponseEntity(ApiExceptionData.builder()// @formatter:off
		        .subExceptions(buildCausesList(ex))
		        .status(HttpStatus.BAD_REQUEST)
		        .message(error).build()// @formatter:on
        );
    }
    
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        logException(ex);
        final ApiExceptionData apiException = ApiExceptionData.builder()// @formatter:off
                .subExceptions(buildCausesList(ex))
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .debugMessage("Error during converting the response")
                .message(ex.getMessage()).build();// @formatter:on
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    protected ResponseEntity<Object> handleInvalidJwtToken(InvalidJwtAuthenticationException exception) {
        logException(exception);
        final ApiExceptionData apiException = ApiExceptionData.builder()// @formatter:off
                .subExceptions(buildCausesList(exception))
                .status(HttpStatus.UNAUTHORIZED)
                .message(exception.getMessage()).build();// @formatter:on
        return buildResponseEntity(apiException);
    }

    private void logException(Exception exception) {
        log.error("Resolved exception: ", exception);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    protected ResponseEntity<Object> handleInternalServerError(InternalServerErrorException exception) {
        logException(exception);
        final ApiExceptionData apiException = ApiExceptionData.builder()// @formatter:off
                .subExceptions(buildCausesList(exception))
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .debugMessage(exception.getCause().getMessage())
                .message(exception.getMessage()).build();// @formatter:on
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException exception) {
        logException(exception);
        final ApiExceptionData apiException = ApiExceptionData.builder()// @formatter:off
                .subExceptions(buildCausesList(exception))
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage()).build();// @formatter:on
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException exception) {
        logException(exception);
        final ApiExceptionData apiException = ApiExceptionData.builder()// @formatter:off
                .subExceptions(buildCausesList(exception))
                .status(HttpStatus.BAD_REQUEST)
                .message(exception.getMessage()).build();// @formatter:on
        return buildResponseEntity(apiException);
    }

    private List<ApiSubExceptionData> buildCausesList(Exception exception) {
        logException(exception);
        List<ApiSubExceptionData> causes = new LinkedList<>();
        Throwable exIt = exception.getCause();
        while (exIt != null) {
            causes.add(new ApiSubExceptionData(exIt));
            exIt = exIt.getCause();
        }
        return causes;
    }

    private ResponseEntity<Object> buildResponseEntity(ApiExceptionData apiException) {
        return new ResponseEntity<>(apiException, apiException.getStatus());
    }
}
