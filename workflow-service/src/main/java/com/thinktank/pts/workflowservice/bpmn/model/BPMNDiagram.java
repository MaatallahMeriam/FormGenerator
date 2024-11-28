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
 * 
 * @author hedfim
 * @since 2023-11-07
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "BPMNDiagram", namespace = "http://www.omg.org/spec/BPMN/20100524/DI")
@XmlAccessorType(XmlAccessType.FIELD)
public class BPMNDiagram {

	@XmlAttribute
	private String id;

	@XmlElement(name = "BPMNPlane", namespace = "http://www.omg.org/spec/BPMN/20100524/DI")
	private BPMNPlane bpmnPlane;

}
