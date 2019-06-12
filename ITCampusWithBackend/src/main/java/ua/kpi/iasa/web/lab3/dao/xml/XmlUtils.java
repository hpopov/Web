package ua.kpi.iasa.web.lab3.dao.xml;

import java.util.Optional;

import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class XmlUtils {
//	private static final Logger log = LoggerFactory.getLogger(XmlUtils.class);
	
	private static final DocumentBuilderFactory DOCUMENT_BUILD_FACTORY;
	
	static {
		DOCUMENT_BUILD_FACTORY = DocumentBuilderFactory.newInstance();
	}
	
	public static String innerValue(Node node) throws XMLParseException {
		if (node == null) {
			throw new XMLParseException("Node must not be null!");
		}
		return node.getNodeValue();
	}

	public static Element toElement(Node node) throws XMLParseException {
		if (node == null) {
			throw new XMLParseException("Node must not be null!");
		}
		if (node.getNodeType() != Node.ELEMENT_NODE) {
			throw new XMLParseException("Node must describe an element");
		}
		return (Element) node;
	}
	
	public static String getAttributeValue(Node node, String attr, String defaultValue) throws XMLParseException {
		NamedNodeMap attributes = node.getAttributes();
		if (attributes == null) {
			throw new XMLParseException("Node has to have at least one attribute!");
		}
		return Optional.ofNullable(attributes.getNamedItem(attr)).map(Node::getNodeValue)
				.orElse(defaultValue);
	}
	
	public static DocumentBuilder makeDocumentBuilder() throws ParserConfigurationException {
			return DOCUMENT_BUILD_FACTORY.newDocumentBuilder();
	}
}
