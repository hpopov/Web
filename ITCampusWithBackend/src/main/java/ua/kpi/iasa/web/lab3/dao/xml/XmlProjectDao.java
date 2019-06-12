package ua.kpi.iasa.web.lab3.dao.xml;

import java.util.List;

import javax.management.modelmbean.XMLParseException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.ProjectDao;
import ua.kpi.iasa.web.lab3.model.ProjectModel;

@Repository
@Qualifier("projectDao")
public class XmlProjectDao extends AbstractXmlDao implements ProjectDao {

	@Override
	public List<ProjectModel> getAllProjects() throws DaoException {
		Document doc = initDocument(getClass().getResourceAsStream("/project.xml"));
		Node rootNode = doc.getElementsByTagName("projects").item(0);
		try {
			return parseListByTagName(rootNode, "project", this::parseModel);
		} catch (XMLParseException e) {
			throw new DaoException("", e);
		}
	}
	
	private ProjectModel parseModel(Node node) throws XMLParseException {
		ProjectModel result = new ProjectModel();
		Element element = XmlUtils.toElement(node);
		result.setUserId(getChildIntContent(element, "userId"));
		result.setProjectId(getChildIntContent(element, "projectId"));
		result.setImageName(getChildTextContent(element, "imageName"));
		result.setHeader(getChildTextContent(element, "header"));
		result.setDescription(getChildTextContent(element, "description"));
		return result;
	}
	

}
