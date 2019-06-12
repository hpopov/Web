package ua.kpi.iasa.web.lab3.exception;

public abstract class ApiSubExceptionData {

}

//@Data
//@EqualsAndHashCode(callSuper = false)
//@AllArgsConstructor
class ApiValidationException extends ApiSubExceptionData {
   private String object;
   private String field;
   private Object rejectedValue;
   private String message;

   ApiValidationException(String object, String message) {
       this.object = object;
       this.message = message;
   }
}