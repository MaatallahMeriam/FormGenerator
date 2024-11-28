package com.thinktank.pts.workflowservice.bpmn.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class represents BPMN Process
 * 
 * @author hedfim
 * @since 2023-11-07
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "process", namespace = "http://www.omg.org/spec/BPMN/20100524/MODEL")
@XmlAccessorType(XmlAccessType.FIELD)
public class Process {

	@XmlAttribute
	private String id;

	@XmlAttribute
	private boolean isExecutable;

	@XmlElement(name = "task", namespace = "http://www.omg.org/spec/BPMN/20100524/MODEL")
	private List<TaskEvent> tasks;

	@XmlElement(name = "sequenceFlow", namespace = "http://www.omg.org/spec/BPMN/20100524/MODEL")
	private List<SequenceFlow> sequenceFlows;

}
