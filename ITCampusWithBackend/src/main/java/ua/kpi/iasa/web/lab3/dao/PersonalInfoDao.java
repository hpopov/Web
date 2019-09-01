package ua.kpi.iasa.web.lab3.dao;

import java.util.Optional;

import ua.kpi.iasa.web.lab3.data.PersonalInfoData;
import ua.kpi.iasa.web.lab3.model.PersonalInfoModel;
import ua.kpi.iasa.web.lab3.model.UserModel;

public interface PersonalInfoDao {
	Optional<PersonalInfoModel> getPersonalInfoForUser(UserModel user) throws DaoException;
	PersonalInfoModel saveOrUpdatePersonalInfoForUser(PersonalInfoData personalInfo,
			UserModel user) throws DaoException;
}
