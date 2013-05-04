package xml.jaxp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserDemo {

	static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

	static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

	static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, FileNotFoundException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		SAXParser parser = factory.newSAXParser();
		try {
			parser.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
	    }
	    catch (SAXNotRecognizedException x){
	        System.err.println("Error: JAXP SAXParser property not recognized: "
	                           + JAXP_SCHEMA_LANGUAGE);
	        System.err.println( "Check to see if parser conforms to the JAXP spec.");
	        System.exit(1);
	    }
		XMLReader xmlReader = parser.getXMLReader();
		xmlReader.setContentHandler(new SAXXMLReader());
		xmlReader.parse(new InputSource(new BufferedInputStream(
				new FileInputStream(new File("./bin/xml/jaxp/build.xml")))));
	}

	private static class SAXXMLReader extends DefaultHandler {

		@Override
		public void setDocumentLocator(Locator locator) {
			System.out
					.println("SAXParserDemo.SAXXMLReader.setDocumentLocator()");
		}

		@Override
		public void startDocument() throws SAXException {
			System.out.println("SAXParserDemo.SAXXMLReader.startDocument()");
		}

		@Override
		public void endDocument() throws SAXException {
			System.out.println("SAXParserDemo.SAXXMLReader.endDocument()");
		}

		@Override
		public void startPrefixMapping(String prefix, String uri)
				throws SAXException {
			System.out
					.println("startPrefixMapping[" + prefix + ":" + uri + "]");
		}

		@Override
		public void endPrefixMapping(String prefix) throws SAXException {
			System.out.println("endPrefixMapping[" + prefix + "]");
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes atts) throws SAXException {
			System.out.println("startElement[" + uri + ":" + localName + ":"
					+ qName + "]");
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			System.out.println("startElement[" + uri + ":" + localName + ":"
					+ qName + "]");
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			System.out.println("characters[" + getString(ch, start, length)
					+ "]");
		}

		@Override
		public void ignorableWhitespace(char[] ch, int start, int length)
				throws SAXException {
			System.out.println("ignorableWhitespace["
					+ getString(ch, start, length) + "]");
		}

		@Override
		public void processingInstruction(String target, String data)
				throws SAXException {
			System.out.println("processingInstruction[" + target + ":" + data
					+ "]");
		}

		@Override
		public void skippedEntity(String name) throws SAXException {
			System.out.println("skippedEntity[" + name + "]");
		}

		private String getString(char[] ch, int start, int length) {
			char[] newCh = new char[length];
			System.out.println("SAXParserDemo.SAXXMLReader.getString() : "
					+ start + ":" + length);
			System.arraycopy(ch, start, newCh, 0, length);
			return new String(newCh);
		}

	}

}
