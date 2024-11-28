package com.thinktank.pts.workflowservice.bpmn.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class represents BPMN Bounds
 * 
 * @author hedfim
 * @since 2023-11-07
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "Bounds", namespace = "http://www.omg.org/spec/DD/20100524/DC")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bounds {

	@XmlAttribute
	private int x;

	@XmlAttribute
	private int y;

	@XmlAttribute
	private int width;

	@XmlAttribute
	private int height;

}
