package ua.kpi.iasa.web.lab3.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.PersonalInfoDao;
import ua.kpi.iasa.web.lab3.data.PersonalInfoData;
import ua.kpi.iasa.web.lab3.model.PersonalInfoModel;
import ua.kpi.iasa.web.lab3.service.PersonalInfoService;
import ua.kpi.iasa.web.lab3.service.exception.EntityNotFoundException;

@Service
public class DefaultPersonalInfoService implements PersonalInfoService {
	
	private static final Logger LOG = LoggerFactory.getLogger(PersonalInfoData.class);
	
	@Autowired
	PersonalInfoDao personalInfoDao;
	
	@Override
	public PersonalInfoModel getPersonalInfoByUserId(int userId) throws EntityNotFoundException {
		try {
			return personalInfoDao.getAllPersonalInfos()
					.stream().filter(model-> model.getUserId() == userId).findAny()
					.orElseThrow(DaoException::new);
		} catch (DaoException e) {
			throw new EntityNotFoundException(e);
		}
	}
	
	@Override
	public boolean updatePersonalInfoByUserId(PersonalInfoData personalInfo, int userId) {
		try {
			personalInfoDao.updatePersonalInfo(personalInfo, userId);
		} catch (DaoException e) {
			LOG.error("Error updating personal info!", e);
			return false;
		}
		return true;
	}
}
