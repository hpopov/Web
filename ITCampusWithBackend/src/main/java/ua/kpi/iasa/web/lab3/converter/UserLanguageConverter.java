package ua.kpi.iasa.web.lab3.converter;

import org.springframework.stereotype.Component;

import ua.kpi.iasa.web.lab3.model.UserLanguageModel;

@Component
public class UserLanguageConverter implements Converter<UserLanguageModel, String[]>{

	@Override
	public String[] modelToData(UserLanguageModel model) {
		return new String[]{model.getLanguage(), model.getAssessment()};
	}

	@Override
	public UserLanguageModel populateDataToModel(UserLanguageModel model, String[] data) {
		if (model == null) {
			model = new UserLanguageModel();
		}
		if (data.length < 2) {
			throw new ConverterException(data, UserLanguageModel.class);
		}
		model.setLanguage(data[0]);
		model.setAssessment(data[1]);
		return model;
	}

}
