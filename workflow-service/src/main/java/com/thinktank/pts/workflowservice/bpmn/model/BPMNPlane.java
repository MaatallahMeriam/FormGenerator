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
 * Class represents BPMN Planes
 * 
 * @author hedfim
 * @since 2023-11-07
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "BPMNPlane", namespace = "http://www.omg.org/spec/BPMN/20100524/DI")
@XmlAccessorType(XmlAccessType.FIELD)
public class BPMNPlane {

	@XmlAttribute
	private String id;

	@XmlElement(name = "BPMNShape", namespace = "http://www.omg.org/spec/BPMN/20100524/DI")
	private List<BPMNShape> bpmnShapes;

	@XmlElement(name = "BPMNEdge", namespace = "http://www.omg.org/spec/BPMN/20100524/DI")
	private List<BPMNEdge> bpmnEdges;

	@XmlAttribute
	private String bpmnElement;

}
