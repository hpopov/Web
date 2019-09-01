package ua.kpi.iasa.web.lab3.converter;

@SuppressWarnings("serial")
public class ConverterException extends RuntimeException {

	private static final String MSG_PATTERN = "Unable to convert %s to %s";

	public ConverterException(Object object, Class<?> clazz, Throwable th) {
		super(buildMessage(object, clazz), th);
	}

	private static String buildMessage(Object object, Class<?> clazz) {
		return String.format(MSG_PATTERN, object, clazz);
	}

	public ConverterException(Object object, Class<?> clazz) {
		super(buildMessage(object, clazz));
	}
	
}
