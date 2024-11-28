package com.thinktank.pts.workflowservice.bpmn.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class represents BPMN Sequence flow
 * 
 * @author hedfim
 * @since 2023-11-07
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "sequenceFlow", namespace = "http://www.omg.org/spec/BPMN/20100524/MODEL")
@XmlAccessorType(XmlAccessType.FIELD)
public class SequenceFlow {

	@XmlAttribute
	private String id;

	@XmlAttribute
	private String sourceRef;

	@XmlAttribute
	private String targetRef;

	@XmlAttribute(name = "status")
	private String status;

}
