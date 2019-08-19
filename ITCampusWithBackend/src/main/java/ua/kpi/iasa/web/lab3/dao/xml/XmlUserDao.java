package ua.kpi.iasa.web.lab3.dao.xml;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.modelmbean.XMLParseException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.UserDao;
import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.model.AuthorityModel;
import ua.kpi.iasa.web.lab3.model.AuthorityType;
import ua.kpi.iasa.web.lab3.model.UserDetailsModel;
import ua.kpi.iasa.web.lab3.model.UserModel;


@Repository("userDao")
public class XmlUserDao extends AbstractXmlDao implements UserDao {

	private static final String PATH = "/user.xml";
	@Override
	public Optional<UserModel> findUserByUsername(String username) throws DaoException {

		Document doc = initDocument(loadInputStream(ABSOLUTE + PATH));
		Node rootNode = doc.getElementsByTagName("users").item(0);
		List<UserModel> users;
		try {
			users = parseListByTagName(rootNode, "user", this::parseModel);
		} catch (XMLParseException e) {
			throw new DaoException("", e);
		}
		return users.stream().filter(user-> 
			user.getUserDetails().getUsername().equals(username)).findFirst();
	}

	private UserModel parseModel(Node node) throws XMLParseException {
		Element element = XmlUtils.toElement(node);
		String username = getChildTextContent(element, "username");
		String password = getChildTextContent(element, "passwordEncoded");
		List<String> authorities = parseListByTagName(element, "authority",
				listNode-> XmlUtils.toElement(listNode).getTextContent());
		final UserDetailsModel userDetailsModel = new UserDetailsModel();
		userDetailsModel.setAuthorities(authorities.stream().map(AuthorityType::valueOf)
				.map(type -> {
					AuthorityModel role = new AuthorityModel();
					role.setAuthorityRole(type);
					return role;
				}).collect(Collectors.toSet()));
		userDetailsModel.setUsername(username);
		userDetailsModel.setPassword(password);
		UserModel result = new UserModel(/*username, password, authorities*/);
		result.setId(getChildIntContent(element, "id"));
		result.setName(getChildTextContent(element, "name"));
		result.setSurname(getChildTextContent(element, "surname"));
		result.setUserDetails(userDetailsModel);
		return result;
	}

	@Override
	public void updateUser(UserData user) throws DaoException {
		Document doc = initDocument(loadInputStream(ABSOLUTE + PATH));
		Node rootNode = doc.getElementsByTagName("users").item(0);

		NodeList userNodes;
		try {
			userNodes = XmlUtils.toElement(rootNode).getElementsByTagName("user");
		} catch (XMLParseException e) {
			throw new DaoException("",e);
		}
		String userId = String.valueOf(user.getId());
		for(int i = 0; i<userNodes.getLength(); i++) {
			Element element;
			try {
				element = XmlUtils.toElement(userNodes.item(i));
			} catch (XMLParseException e) {
				throw new DaoException("",e);
			}
			if (element.getElementsByTagName("id").item(0).getTextContent().equals(userId)) {
				System.out.println("Id: "+ element.getElementsByTagName("id").item(0).getTextContent());
				element.getElementsByTagName("name").item(0).setTextContent(user.getName());
				System.out.println("name: "+ element.getElementsByTagName("name").item(0).getTextContent());
				element.getElementsByTagName("surname").item(0)
				.setTextContent(user.getSurname());
				System.out.println("surname: "+ element.getElementsByTagName("surname").item(0).getTextContent());
				element.getElementsByTagName("username").item(0)
				.setTextContent(user.getLogin());
				System.out.println("username: "+ element.getElementsByTagName("username")
				.item(0).getTextContent());
			}
		}

		Transformer xformer;
		try {
			xformer = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
			throw new DaoException("",e);
		}
		try {
			final File file = new File(ABSOLUTE + PATH);
			System.out.println(file);
			xformer.transform(new DOMSource(doc), new StreamResult(file));
		} catch (TransformerException e) {
			throw new DaoException("",e);
		}
	}

}
