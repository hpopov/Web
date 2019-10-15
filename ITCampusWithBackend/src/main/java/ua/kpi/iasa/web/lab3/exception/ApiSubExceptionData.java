package ua.kpi.iasa.web.lab3.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiSubExceptionData {
    private String exceptionName;
    private String message;
    
    public ApiSubExceptionData(Throwable ex) {
        exceptionName = ex.getClass().getSimpleName();
        message = ex.getMessage();
    }
}

//@Data
//@EqualsAndHashCode(callSuper = false)
//@AllArgsConstructor
//class ApiValidationException extends ApiSubExceptionData {
//   private String object;
//   private String field;
//   private Object rejectedValue;
//   private String message;
//
//   ApiValidationException(String object, String message) {
//       this.object = object;
//       this.message = message;
//   }
//}