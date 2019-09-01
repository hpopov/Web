package ua.kpi.iasa.web.lab3.service;

import ua.kpi.iasa.web.lab3.data.PersonalInfoData;
import ua.kpi.iasa.web.lab3.model.PersonalInfoModel;
import ua.kpi.iasa.web.lab3.service.exception.EntityNotFoundException;

public interface PersonalInfoService {

	PersonalInfoModel getPersonalInfoByUsername(String username) throws EntityNotFoundException;

	boolean updatePersonalInfoByUsername(PersonalInfoData personalInfo, String username);

}