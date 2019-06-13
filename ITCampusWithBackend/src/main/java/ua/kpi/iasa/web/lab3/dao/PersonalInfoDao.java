package ua.kpi.iasa.web.lab3.dao;

import java.util.List;

import ua.kpi.iasa.web.lab3.data.PersonalInfoData;
import ua.kpi.iasa.web.lab3.model.PersonalInfoModel;

public interface PersonalInfoDao {

	List<PersonalInfoModel> getAllPersonalInfos() throws DaoException;
	void updatePersonalInfo(PersonalInfoData personalInfo, int userId) throws DaoException;
}
