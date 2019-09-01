package ua.kpi.iasa.web.lab3.converter;

import org.springframework.stereotype.Component;

import ua.kpi.iasa.web.lab3.model.UserSkillModel;

@Component
public class UserSkillConverter implements Converter<UserSkillModel, String[]> {

	@Override
	public String[] modelToData(UserSkillModel model) {
		return new String[] {model.getName(), model.getAssessment()};
	}

	@Override
	public UserSkillModel populateDataToModel(UserSkillModel model, String[] data) {
		if (model == null) {
			model = new UserSkillModel();
		}
		if (data.length < 2) {
			throw new ConverterException(data, UserSkillModel.class);
		}
		model.setName(data[0]);
		model.setAssessment(data[1]);
		return model;
	}

}
