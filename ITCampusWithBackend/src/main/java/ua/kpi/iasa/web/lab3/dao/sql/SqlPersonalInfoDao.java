package ua.kpi.iasa.web.lab3.dao.sql;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.kpi.iasa.web.lab3.converter.PersonalInfoConverter;
import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.PersonalInfoDao;
import ua.kpi.iasa.web.lab3.dao.UserDao;
import ua.kpi.iasa.web.lab3.data.PersonalInfoData;
import ua.kpi.iasa.web.lab3.model.PersonalInfoModel;
import ua.kpi.iasa.web.lab3.model.UserModel;

@Primary
@Repository
public class SqlPersonalInfoDao extends AbstractSqlDao implements PersonalInfoDao {

	@Autowired
	@Qualifier("sqlUserDao")
	private UserDao sqlUserDao;
	
	@Autowired
	private PersonalInfoConverter personalInfoConverter;
	
	@Transactional
	@Override
	public Optional<PersonalInfoModel> getPersonalInfoForUser(UserModel user) throws DaoException {
		return Optional.ofNullable(user.getPersonalInfo());
	}

//	@Transactional Anyway, service has to manage transaction, because there will be getUser() also
	@Override
	public PersonalInfoModel saveOrUpdatePersonalInfoForUser(PersonalInfoData personalInfo, UserModel user)
			throws DaoException {
//		personalInfo.getUser().setLogin(user.getUsername()); It must be completed in controller/service
		//fetching personalInfo from db if there is one
		Optional<PersonalInfoModel> optionalPersonalInfo = getPersonalInfoForUser(user);
		log.info("Updating personalInfo, was: {}",optionalPersonalInfo);
		final PersonalInfoModel personalInfoModel = personalInfoConverter
				.populateDataToModel(optionalPersonalInfo.get(), personalInfo);
		log.info("Updating personalInfo, became: {}",personalInfoModel);
//		entityManager.merge(personalInfoModel);
		return personalInfoModel;
	}

}
