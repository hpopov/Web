package ua.kpi.iasa.web.lab3.dao.xml;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.UserDetailsDao;
import ua.kpi.iasa.web.lab3.model.UserDetailsModel;
import ua.kpi.iasa.web.lab3.model.UserModel;

@Repository
public class XmlUserDetailsDao implements UserDetailsDao {

	@Autowired
	private XmlUserDao xmlUserDao;
	
	@Override
	public Optional<UserDetailsModel> getUserDetailsByUsername(String username)
			throws DaoException {
		return xmlUserDao.findUserByUsername(username).map(UserModel::getUserDetails);
	}

}
