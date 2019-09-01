package ua.kpi.iasa.web.lab3.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.kpi.iasa.web.lab3.data.PersonalInfoData;
import ua.kpi.iasa.web.lab3.model.PersonalInfoModel;
import ua.kpi.iasa.web.lab3.model.UserModel;

@Component
public class PersonalInfoConverter implements Converter<PersonalInfoModel, PersonalInfoData>{

	@Autowired
	private DateConverter dateConverter;
	@Autowired
	private UserLanguageConverter userLanguageConverter;
	@Autowired
	private UserSkillConverter userSkillConverter;
	@Autowired
	private PublicUserConverter publicUserConverter;
	
	@Override
	public PersonalInfoData modelToData(PersonalInfoModel model) {
		final PersonalInfoData data = new PersonalInfoData();
		data.setCity(model.getCity());
		data.setDateOfBirth(dateConverter.modelToData(model.getDateOfBirth()));
		data.setEducation(model.getEducation());
		data.setEducationBeginYear(model.getStartEducationYear());
		data.setEducationEndYear(model.getFinishEducationYear());
		data.setFaculty(model.getFaculty());
		data.setPhoneNumber(model.getPhoneNumber());
		data.setSkills(Converter.mapCollectionToList(userSkillConverter::modelToData,
				model.getSkills()));
		data.setLanguages(Converter.mapCollectionToList(userLanguageConverter::modelToData,
				model.getLanguages()));
		data.setUser(publicUserConverter.modelToData(model.getUser()));
		return data;
	}

	
	@Override
	public PersonalInfoModel populateDataToModel(PersonalInfoModel model, PersonalInfoData data) {
		if (model == null) {
			model = new PersonalInfoModel();
		}
		model.setCity(data.getCity());
		model.setDateOfBirth(dateConverter.dataToModel(data.getDateOfBirth()));
		model.setEducation(data.getEducation());
		model.setFaculty(data.getFaculty());
		model.setStartEducationYear(data.getEducationBeginYear());
		model.setFinishEducationYear(data.getEducationEndYear());
		model.setLanguages(Converter.mapCollectionToSet(userLanguageConverter::dataToModel,
				data.getLanguages()));
		model.setSkills(Converter.mapCollectionToSet(userSkillConverter::dataToModel,
				data.getSkills()));
		model.setPhoneNumber(data.getPhoneNumber());
		final UserModel userModel = publicUserConverter.populateDataToModel(model.getUser(),
				data.getUser());
		model.setUser(userModel);
		return model;
	}

}
