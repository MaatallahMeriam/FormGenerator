package com.thinktank.pts.workflowservice.bpmn.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class represents BPMN Definitions
 * 
 * @author hedfim
 * @since 2023-11-07
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "definitions", namespace = "http://www.omg.org/spec/BPMN/20100524/MODEL")
@XmlAccessorType(XmlAccessType.FIELD)
public class Definitions {

	@XmlAttribute(name = "xsi")
	private String xsi;

	@XmlAttribute(name = "bpmn")
	private String bpmn;

	@XmlAttribute(name = "bpmndi")
	private String bpmndi;

	@XmlAttribute(name = "dc")
	private String dc;

	@XmlAttribute(name = "di")
	private String di;

	@XmlAttribute
	private String id;

	@XmlAttribute
	private String targetNamespace;

	@XmlElement(name = "process", namespace = "http://www.omg.org/spec/BPMN/20100524/MODEL")
	private Process process;

	@XmlElement(name = "BPMNDiagram", namespace = "http://www.omg.org/spec/BPMN/20100524/DI")
	private BPMNDiagram bpmnDiagram;

}
