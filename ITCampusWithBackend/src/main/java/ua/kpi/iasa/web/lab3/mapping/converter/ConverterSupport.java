package ua.kpi.iasa.web.lab3.mapping.converter;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ConverterSupport {

	private ConverterSupport() {}
	
	public static <T> Stream<T> streamOf(Iterable<T> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false);
	}

}
