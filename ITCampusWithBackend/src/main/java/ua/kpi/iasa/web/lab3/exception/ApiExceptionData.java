package ua.kpi.iasa.web.lab3.exception;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ApiExceptionData {
    
    private static final String DEFAULT_ERROR_MESSAGE = "Unexpected error";
    
    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
//    @Builder.Default
    private final Date date = Calendar.getInstance().getTime();
    @Builder.Default
    private String message = DEFAULT_ERROR_MESSAGE;
	private String debugMessage;
	private List<ApiSubExceptionData> subExceptions;
}
