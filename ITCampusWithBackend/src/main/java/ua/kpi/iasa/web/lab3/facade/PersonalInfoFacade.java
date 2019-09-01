package ua.kpi.iasa.web.lab3.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.converter.PersonalInfoConverter;
import ua.kpi.iasa.web.lab3.data.PersonalInfoData;
import ua.kpi.iasa.web.lab3.model.PersonalInfoModel;
import ua.kpi.iasa.web.lab3.service.PersonalInfoService;

@Service
public class PersonalInfoFacade {
	
	@Autowired
	private PersonalInfoService personalInfoService;
	@Autowired
	private PersonalInfoConverter personalInfoConverter;

	public PersonalInfoData getPersonalInfoByUsername(String username) {
		PersonalInfoModel personalInfoModel = personalInfoService.getPersonalInfoByUsername(username);
		return personalInfoConverter.modelToData(personalInfoModel);
	}
}
