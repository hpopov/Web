package ua.kpi.iasa.web.lab3.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class DateConverter implements Converter<Date, String> {

	private DateFormat dateFormat;
	//Pattern 1999-03-25
	private final String pattern = "yyyy-MM-dd";

	@PostConstruct
	private void initialize() {
		dateFormat = new SimpleDateFormat(pattern);
	}
	@Override
	public String modelToData(Date model) {
		if (model == null) {
			return null;
		}
		return dateFormat.format(model);
	}

	@Override
	public Date populateDataToModel(Date model, String data) {
		try {
			Date date = data == null? null: dateFormat.parse(data);
			if (model == null) {
				return date;
			} else {
				model.setTime(date.getTime());
			}
		} catch (ParseException e) {
			throw new ConverterException(data, String.class, e);
		}
		return model;
	}

}
