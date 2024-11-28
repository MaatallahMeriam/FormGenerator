package com.thinktank.pts.workflowservice.bpmn.service;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

import com.thinktank.pts.workflowservice.bpmn.model.Definitions;

/**
 * Service for xml marshall and unmarshall XML
 * 
 * @author hedfim
 * @since 2023-11-20
 *
 */
@Service
public class XmlParser {

	/**
	 * marshalls the model definitions to XML format
	 * 
	 * @param definitions
	 *            the java object to marshall
	 * @return string contain the xml
	 */
	public String marshalToXML(Definitions definitions) {
		StringWriter writer = new StringWriter();
		jaxb2Marshaller().marshal(definitions, new StreamResult(writer));
		return writer.toString();
	}

	/**
	 * unmarshals the given xml string to definition model
	 * 
	 * @param xmlString
	 *            the xml to unmarshal
	 * @return the definition object
	 */
	public Definitions unmarshalToDefinition(String xmlString) {
		try {
			Unmarshaller unmarshal = jaxb2Unmarshaller();
			return (Definitions) unmarshal.unmarshal(new StringReader(xmlString));
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Creates jaxb marshller
	 * 
	 * @return Jaxb2Marshaller
	 */
	private Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(Definitions.class); // Add all classes to be bound
		return marshaller;
	}

	/**
	 * Creates jaxb unmarshller
	 * 
	 * @return Unmarshaller object
	 * @throws JAXBException
	 */
	private Unmarshaller jaxb2Unmarshaller() {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Definitions.class);
			return jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}

	}

}
