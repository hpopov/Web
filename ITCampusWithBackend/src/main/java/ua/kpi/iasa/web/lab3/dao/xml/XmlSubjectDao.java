package ua.kpi.iasa.web.lab3.dao.xml;

import java.io.InputStream;
import java.util.List;

import javax.management.modelmbean.XMLParseException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.SubjectDao;
import ua.kpi.iasa.web.lab3.model.SubjectModel;
import ua.kpi.iasa.web.lab3.model.TestType;

@Repository
@Qualifier("subjectDao")
public class XmlSubjectDao extends AbstractXmlDao implements SubjectDao {

	@Override
	public List<SubjectModel> getAllSubjects()  throws DaoException {
		Document doc = initDocument(getClass().getResourceAsStream("/subject.xml"));
		Node rootNode = doc.getElementsByTagName("subjects").item(0);
		try {
			return parseListByTagName(rootNode, "subject", this::parseModel);
		} catch (XMLParseException e) {
			throw new DaoException("", e);
		}
	}
	
	private SubjectModel parseModel(Node node) throws XMLParseException {
		SubjectModel result = new SubjectModel();
		Element element = XmlUtils.toElement(node);
		result.setUserId(getChildIntContent(element, "userId"));
		result.setSubjectId(getChildIntContent(element, "subjectId"));
		result.setAcademicHours(getChildIntContent(element, "academicHours"));
		result.setMark(getChildIntContent(element, "mark"));
		result.setName(getChildTextContent(element, "name"));
		result.setTeacher(getChildTextContent(element, "teacher"));
		result.setTestType(TestType.values()[getChildIntContent(element, "testType")]);
		return result;
	}
	
}
