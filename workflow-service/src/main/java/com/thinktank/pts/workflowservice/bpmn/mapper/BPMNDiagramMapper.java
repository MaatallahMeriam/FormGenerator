package com.thinktank.pts.workflowservice.bpmn.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thinktank.pts.workflowservice.bpmn.model.BPMNDiagram;
import com.thinktank.pts.workflowservice.bpmn.model.BPMNEdge;
import com.thinktank.pts.workflowservice.bpmn.model.BPMNPlane;
import com.thinktank.pts.workflowservice.bpmn.model.BPMNShape;

/**
 * Class mapper for bpmn definitions
 * 
 * @author hedfim
 * @since 2023-11-20
 *
 */
@Service
public class BPMNDiagramMapper {

	/**
	 * Creates bpmn diagram by the given shapes and edges
	 * 
	 * @param shapes
	 *            bpmn shapes related to the diagram
	 * @param edges
	 *            bpmn edges related to the diagram
	 * @return bpmn diagram created
	 */
	public BPMNDiagram create(List<BPMNShape> shapes, List<BPMNEdge> edges) {
		BPMNDiagram bpmnDiagram = new BPMNDiagram();
		bpmnDiagram.setId("status_workflow_diagram");
		bpmnDiagram.setBpmnPlane(new BPMNPlane());
		bpmnDiagram.getBpmnPlane().setId("status_workflow_plane");
		bpmnDiagram.getBpmnPlane().setBpmnElement("status_workflow_process");
		bpmnDiagram.getBpmnPlane().setBpmnShapes(shapes);
		bpmnDiagram.getBpmnPlane().setBpmnEdges(edges);
		return bpmnDiagram;
	}

}
