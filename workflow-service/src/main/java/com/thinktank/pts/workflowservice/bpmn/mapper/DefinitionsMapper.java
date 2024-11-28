package com.thinktank.pts.workflowservice.bpmn.mapper;

import org.springframework.stereotype.Service;

import com.thinktank.pts.workflowservice.bpmn.model.BPMNDiagram;
import com.thinktank.pts.workflowservice.bpmn.model.Definitions;
import com.thinktank.pts.workflowservice.bpmn.model.Process;

/**
 * Class mapper for bpmn definitions
 * 
 * @author hedfim
 * @since 2023-11-20
 *
 */
@Service
public class DefinitionsMapper {

	/**
	 * Creates bpmn definitions by the given process and diagram
	 * 
	 * @param bpmnDiagram
	 *            the bpmn diagram related to the definitions
	 * @param process
	 *            the bpmn process related to the definitions
	 * @return Definitions created
	 */
	public Definitions create(BPMNDiagram bpmnDiagram, Process process) {
		Definitions definitions = new Definitions();
		definitions.setId("status_workflow_def");
		definitions.setBpmn("http://www.omg.org/spec/BPMN/20100524/MODEL");
		definitions.setBpmndi("http://www.omg.org/spec/BPMN/20100524/DI");
		definitions.setXsi("http://www.w3.org/2001/XMLSchema-instance");
		definitions.setDc("http://www.omg.org/spec/DD/20100524/DC");
		definitions.setDi("http://www.omg.org/spec/DD/20100524/DI");
		definitions.setTargetNamespace("http://bpmn.io/schema/bpmn");
		definitions.setProcess(process);
		definitions.setBpmnDiagram(bpmnDiagram);
		return definitions;
	}

}
