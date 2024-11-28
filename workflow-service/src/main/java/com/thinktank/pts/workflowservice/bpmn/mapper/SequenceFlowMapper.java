package com.thinktank.pts.workflowservice.bpmn.mapper;

import org.springframework.stereotype.Service;

import com.thinktank.pts.workflowservice.bpmn.model.SequenceFlow;

@Service
public class SequenceFlowMapper {

	public SequenceFlow create(String sourceRef, String targetRef) {
		SequenceFlow sequenceFlow = new SequenceFlow();
		sequenceFlow.setSourceRef(sourceRef);
		sequenceFlow.setTargetRef(targetRef);
		return sequenceFlow;
	}

}
