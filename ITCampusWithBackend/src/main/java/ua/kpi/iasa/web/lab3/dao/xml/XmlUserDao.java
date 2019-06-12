package ua.kpi.iasa.web.lab3.dao.xml;

import java.util.List;
import java.util.Optional;

import javax.management.modelmbean.XMLParseException;

import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.UserDao;
import ua.kpi.iasa.web.lab3.model.UserModel;


@Repository("userDao")
public class XmlUserDao extends AbstractXmlDao implements UserDao {

	@Override
	public Optional<UserModel> findUserByUsername(String username) throws DaoException {

		Document doc = initDocument(getClass().getResourceAsStream("/user.xml"));
		Node rootNode = doc.getElementsByTagName("users").item(0);
		List<UserModel> users;
		try {
			users = parseListByTagName(rootNode, "user", this::parseModel);
		} catch (XMLParseException e) {
			throw new DaoException("", e);
		}
		return users.stream().filter(user-> user.getUsername().equals(username)).findFirst();
	}

	private UserModel parseModel(Node node) throws XMLParseException {
		Element element = XmlUtils.toElement(node);
		String username = getChildTextContent(element, "username");
		String password = getChildTextContent(element, "passwordEncoded");
		List<String> authorities = parseListByTagName(element, "authority",
				listNode-> XmlUtils.toElement(listNode).getTextContent());
		UserModel result = new UserModel(username, password, authorities);
		result.setId(getChildIntContent(element, "id"));
		result.setName(getChildTextContent(element, "name"));
		result.setSurname(getChildTextContent(element, "surname"));
		return result;
	}
}
