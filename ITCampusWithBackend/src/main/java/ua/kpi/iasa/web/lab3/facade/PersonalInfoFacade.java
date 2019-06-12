package ua.kpi.iasa.web.lab3.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.data.PersonalInfoData;
import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.model.PersonalInfoModel;
import ua.kpi.iasa.web.lab3.service.PersonalInfoService;

@Service
public class PersonalInfoFacade {
	
	@Autowired
	private PersonalInfoService personalInfoService;

	public PersonalInfoData getPersonalInfoByUser(UserData user) {
		PersonalInfoModel personalInfoModel = personalInfoService.getPersonalInfoByUserId(user.getId());
		PersonalInfoData personalInfoData = new PersonalInfoData();
		personalInfoData.setCity(personalInfoModel.getCity());
		personalInfoData.setDateOfBirth(personalInfoModel.getDateOfBirth());
		personalInfoData.setEducation(personalInfoModel.getEducation());
		personalInfoData.setEducationBeginYear(personalInfoModel.getStartEducationYear());
		personalInfoData.setEducationEndYear(personalInfoModel.getFinishEducationYear());
		personalInfoData.setFaculty(personalInfoModel.getFaculty());
		personalInfoData.setPhoneNumber(personalInfoModel.getPhoneNumber());
		
		personalInfoModel.getLanguages().entrySet()
			.forEach(entry-> personalInfoData.addLanguage(entry.getKey(), entry.getValue()));
		
		personalInfoModel.getSkills().entrySet()
			.forEach(entry-> personalInfoData.addSkill(entry.getKey(), entry.getValue()));
		return personalInfoData;
	}
}
