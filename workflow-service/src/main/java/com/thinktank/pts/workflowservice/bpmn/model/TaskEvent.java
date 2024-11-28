package com.thinktank.pts.workflowservice.bpmn.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Class represents BPMN Tasks
 * 
 * @author hedfim
 * @since 2023-11-07
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@XmlRootElement(name = "task", namespace = "http://www.omg.org/spec/BPMN/20100524/MODEL")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskEvent {

	@XmlAttribute
	private String id;

	@XmlElement(name = "incoming", namespace = "http://www.omg.org/spec/BPMN/20100524/MODEL")
	private List<String> incoming;

	@XmlElement(name = "outgoing", namespace = "http://www.omg.org/spec/BPMN/20100524/MODEL")
	private List<String> outgoing;

	@XmlAttribute
	private String name;

	@XmlAttribute(name = "status")
	private String status;

}
