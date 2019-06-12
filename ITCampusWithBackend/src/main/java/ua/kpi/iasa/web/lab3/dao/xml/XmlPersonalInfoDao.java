package ua.kpi.iasa.web.lab3.dao.xml;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.modelmbean.XMLParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.PersonalInfoDao;
import ua.kpi.iasa.web.lab3.model.PersonalInfoModel;

@Repository
@Qualifier("personalInfoDao")
public class XmlPersonalInfoDao extends AbstractXmlDao implements PersonalInfoDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(XmlPersonalInfoDao.class);

	@Override
	public List<PersonalInfoModel> getAllPersonalInfos() throws DaoException {

		Document doc = initDocument(getClass().getResourceAsStream("/personalInfo.xml"));
		Node rootNode = doc.getElementsByTagName("personalInfos").item(0);
		try {
			return parseListByTagName(rootNode, "personalInfo", this::parseModel);
		} catch (XMLParseException e) {
			throw new DaoException("", e);
		}
	}

	private PersonalInfoModel parseModel(Node node) throws XMLParseException {
		PersonalInfoModel result = new PersonalInfoModel();
		Element element = XmlUtils.toElement(node);
		result.setUserId(getChildIntContent(element, "userId"));
		result.setCity(getChildTextContent(element, "city"));
		result.setDateOfBirth(getChildTextContent(element, "dateOfBirth"));
		result.setEducation(getChildTextContent(element, "education"));
		result.setFaculty(getChildTextContent(element, "faculty"));
		result.setFinishEducationYear(getChildIntContent(element, "finishEducationYear"));
		result.setPhoneNumber(getChildTextContent(element, "phoneNumber"));
		result.setStartEducationYear(getChildIntContent(element, "startEducationYear"));
		
		NodeList skillsTags = XmlUtils.toElement(element.getElementsByTagName("skills").item(0))
				.getElementsByTagName("skill");
		result.setSkills(parseList(skillsTags, this::parseEntry).stream()
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue)));
		
		NodeList languagesTags = XmlUtils.toElement(element.getElementsByTagName("languages").item(0))
				.getElementsByTagName("language");
		result.setLanguages(parseList(languagesTags, this::parseEntry).stream()
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue)));
		return result;
	}
	
	private Entry parseEntry(Node skillNode) {
		Entry entry = new Entry();
		Element skillElement;
		try {
			skillElement = XmlUtils.toElement(skillNode);
		} catch (XMLParseException e) {
			LOG.error("Node {} can not be casted to element", skillNode);
			return entry;
		}
		entry.setKey(getChildTextContent(skillElement, "key"));
		entry.setValue(getChildTextContent(skillElement, "value"));
		return entry;
	}


	
}

class Entry {
	private String key;
	private String value;
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
