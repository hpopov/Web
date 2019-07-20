package ua.kpi.iasa.web.lab3.dao.xml;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.modelmbean.XMLParseException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
import ua.kpi.iasa.web.lab3.data.PersonalInfoData;
import ua.kpi.iasa.web.lab3.model.PersonalInfoModel;

@Repository
@Qualifier("personalInfoDao")
public class XmlPersonalInfoDao extends AbstractXmlDao implements PersonalInfoDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(XmlPersonalInfoDao.class);
	private static final String PATH = "/personalInfo.xml";

	@Override
	public List<PersonalInfoModel> getAllPersonalInfos() throws DaoException {

		Document doc = initDocument(loadInputStream(ABSOLUTE + PATH));
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

	@Override
	public void updatePersonalInfo(PersonalInfoData personalInfo, int userId) throws DaoException {
		Document doc = initDocument(loadInputStream(ABSOLUTE + PATH));
		Node rootNode = doc.getElementsByTagName("personalInfos").item(0);

		NodeList personalInfoNodes;
		try {
			personalInfoNodes = XmlUtils.toElement(rootNode).getElementsByTagName("personalInfo");
		} catch (XMLParseException e) {
			throw new DaoException("",e);
		}
		String userIdStr = String.valueOf(userId);
		for(int i = 0; i<personalInfoNodes.getLength(); i++) {
			Element element;
			try {
				element = XmlUtils.toElement(personalInfoNodes.item(i));
			} catch (XMLParseException e) {
				throw new DaoException("",e);
			}
			if (element.getElementsByTagName("userId").item(0).getTextContent().equals(userIdStr)) {
				element.getElementsByTagName("phoneNumber").item(0).setTextContent(personalInfo.getPhoneNumber());
				element.getElementsByTagName("dateOfBirth").item(0).setTextContent(personalInfo.getDateOfBirth());
				element.getElementsByTagName("city").item(0).setTextContent(personalInfo.getCity());
				element.getElementsByTagName("education").item(0).setTextContent(personalInfo.getEducation());
				element.getElementsByTagName("faculty").item(0).setTextContent(personalInfo.getFaculty());
				element.getElementsByTagName("startEducationYear").item(0)
					.setTextContent(String.valueOf(personalInfo.getEducationYear()[0]));
				element.getElementsByTagName("finishEducationYear").item(0)
					.setTextContent(String.valueOf(personalInfo.getEducationYear()[1]));
//				XmlUtils.toElement(element.getElementsByTagName("skills").item(0).re).
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
