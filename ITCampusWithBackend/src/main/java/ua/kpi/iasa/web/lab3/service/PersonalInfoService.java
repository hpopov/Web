package ua.kpi.iasa.web.lab3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.PersonalInfoDao;
import ua.kpi.iasa.web.lab3.model.PersonalInfoModel;

@Service
public class PersonalInfoService {
	
	@Autowired
	PersonalInfoDao personalInfoDao;
	
	public PersonalInfoModel getPersonalInfoByUserId(int userId) throws EntityNotFoundException {
		try {
			return personalInfoDao.getAllPersonalInfos()
					.stream().filter(model-> model.getUserId() == userId).findAny()
					.orElseThrow(DaoException::new);
		} catch (DaoException e) {
			throw new EntityNotFoundException(e);
		}
	}
}
