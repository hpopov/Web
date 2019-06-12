package ua.kpi.iasa.web.lab3.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ua.kpi.iasa.web.lab3.token.InvalidJwtAuthenticationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

//	@Override
//	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		String error = "Malformed JSON request";
//		return buildResponseEntity(new ApiExceptionData(HttpStatus.BAD_REQUEST, error, ex));
//	}
//	
//	@ExceptionHandler(InvalidJwtAuthenticationException.class)
//	   protected ResponseEntity<Object> handleInvalidJwtToken(
//			   InvalidJwtAuthenticationException ex) {
//	       ApiExceptionData apiException = new ApiExceptionData(HttpStatus.UNAUTHORIZED);
//	       apiException.setMessage(ex.getMessage());
//	       return buildResponseEntity(apiException);
//	   }
//
//	private ResponseEntity<Object> buildResponseEntity(ApiExceptionData apiException) {
//		return new ResponseEntity<>(apiException, apiException.getStatus());
//	}
}
