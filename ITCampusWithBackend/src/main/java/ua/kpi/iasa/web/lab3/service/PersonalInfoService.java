package ua.kpi.iasa.web.lab3.service;

import javax.persistence.EntityNotFoundException;

import ua.kpi.iasa.web.lab3.data.PersonalInfoData;
import ua.kpi.iasa.web.lab3.model.PersonalInfoModel;

public interface PersonalInfoService {

    PersonalInfoModel getPersonalInfoByUsername(String username) throws EntityNotFoundException;

    PersonalInfoModel updatePersonalInfoByUsername(PersonalInfoData personalInfo, String username);
}