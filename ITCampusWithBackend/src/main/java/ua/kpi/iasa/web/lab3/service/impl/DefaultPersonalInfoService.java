package ua.kpi.iasa.web.lab3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.kpi.iasa.web.lab3.LoggableComponent;
import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.PersonalInfoDao;
import ua.kpi.iasa.web.lab3.data.PersonalInfoData;
import ua.kpi.iasa.web.lab3.model.PersonalInfoModel;
import ua.kpi.iasa.web.lab3.model.UserModel;
import ua.kpi.iasa.web.lab3.service.PersonalInfoService;
import ua.kpi.iasa.web.lab3.service.UserService;
import ua.kpi.iasa.web.lab3.service.exception.EntityNotFoundException;

@Service
public class DefaultPersonalInfoService extends LoggableComponent implements PersonalInfoService {

	@Autowired
	private UserService userService;
	@Autowired
	private PersonalInfoDao personalInfoDao;
	
	@Override
	public PersonalInfoModel getPersonalInfoByUsername(String username) throws EntityNotFoundException {
		try {
			final UserModel user = userService.getUserByUsername(username);
			return personalInfoDao.getPersonalInfoForUser(user).orElseThrow(EntityNotFoundException::new);
		} catch (DaoException e) {
			throw new EntityNotFoundException(e);
		}
	}
	
//	private PersonalInfoModel makeEmptyPersonalInfoModel(UserModel user) {
//		PersonalInfoModel model = new PersonalInfoModel();
//		model.setLanguages(Collections.emptyMap());
//		model.set
//	}
	
	@Transactional
	@Override
	public boolean updatePersonalInfoByUsername(PersonalInfoData personalInfo, String username) {
		try {
			final UserModel user = userService.getUserByUsername(username);
			personalInfoDao.saveOrUpdatePersonalInfoForUser(personalInfo, user);
		} catch (DaoException e) {
			log.error("Error updating personal info!", e);
			return false;
		}
		return true;
	}
}
