package ua.kpi.iasa.web.lab3.dao.xml;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ua.kpi.iasa.web.lab3.ExceptionableFunction;
import ua.kpi.iasa.web.lab3.dao.DaoException;

public class AbstractXmlDao {
	
	private static final DateFormat DATE_FORMAT;
	static {
		DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	protected <T> List<T> parseList(NodeList nodes,
			ExceptionableFunction<Node, T, XMLParseException> modelParser) throws XMLParseException {
		final int count = nodes.getLength();
		List<T> list = new ArrayList<>(count);
		for(int i = 0; i<count; i++) {
			list.add(modelParser.apply((nodes.item(i))));
		}
		return list;
	}
	
	protected <T> List<T> parseListByTagName(Node node, String tagName,
			ExceptionableFunction<Node, T, XMLParseException> modelParser) throws XMLParseException {
		NodeList nodes = XmlUtils.toElement(node).getElementsByTagName(tagName);
		final int count = nodes .getLength();
		List<T> list = new ArrayList<>(count);
		for(int i = 0; i<count; i++) {
			list.add(modelParser.apply((nodes.item(i))));
		}
		return list;
	}
	
	protected String getChildTextContent(Element element, String childTag) {
		return element.getElementsByTagName(childTag).item(0).getTextContent();
	}
	
	protected int getChildIntContent(Element element, String childTag) {
		return Integer.valueOf(getChildTextContent(element, childTag));
	}
	
//	protected Date getChildDateContent(Element element, String childTag) throws XMLParseException {
//		try {
//			String dateString = getChildTextContent(element, childTag);
//			System.out.println(dateString);
//			return DATE_FORMAT.parse(dateString);
//		} catch (ParseException e) {
//			throw new XMLParseException(e, "Unable to parse date: wrong format");
//		}
//	}

	protected Document initDocument(InputStream sourceIs) throws DaoException {
		try {
			DocumentBuilder dBuilder = XmlUtils.makeDocumentBuilder();
			return dBuilder.parse(sourceIs);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new DaoException("Error while initializing the parser", e);
		}
	}
}
