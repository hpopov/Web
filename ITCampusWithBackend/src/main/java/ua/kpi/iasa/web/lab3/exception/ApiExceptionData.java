package ua.kpi.iasa.web.lab3.exception;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiExceptionData {
	private HttpStatus status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date date;
	private String message;
	private String debugMessage;
	private List<ApiSubExceptionData> subExceptions;

	private ApiExceptionData() {
		date = new Date();
	}

	public ApiExceptionData(HttpStatus status) {
		this();
		this.status = status;
	}

	public ApiExceptionData(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
	}

	public ApiExceptionData(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}

	public HttpStatus getStatus() {
		return status;
	}

	public Date getDate() {
		return date;
	}

	public String getMessage() {
		return message;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public List<ApiSubExceptionData> getSubExceptions() {
		return subExceptions;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
