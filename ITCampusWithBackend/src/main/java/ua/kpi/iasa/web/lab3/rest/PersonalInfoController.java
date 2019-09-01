package ua.kpi.iasa.web.lab3.rest;

import ua.kpi.iasa.web.lab3.data.PersonalInfoData;

public interface PersonalInfoController {

	PersonalInfoData getPersonalInfo(String username);
	boolean updatePersonalInfo(PersonalInfoData personalInfoData, String username);
}
