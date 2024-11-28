package com.thinktank.pts.workflowservice.bpmn.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.workflowservice.bpmn.model.BPMNEdge;
import com.thinktank.pts.workflowservice.bpmn.model.BPMNShape;
import com.thinktank.pts.workflowservice.bpmn.model.SequenceFlow;
import com.thinktank.pts.workflowservice.bpmn.model.WayPoint;
import com.thinktank.pts.workflowservice.model.PtsStatusWayPoint;

@Service
public class BPMNEdgeMapper {

	@Autowired
	private WayPointsMapper wayPointsMapper;

	/**
	 * maps to bpmn egde
	 * 
	 * @param ptsStatusWayPoints
	 *            way points reltaed to the given status
	 * @param sequenceFlow
	 *            sequence flow related to the bpmn egde
	 * @param sourceTask
	 *            task source of bpmn egde
	 * @param targetTask
	 *            task target of bpmn egde
	 * @return BPMNEdge after mapping
	 */
	public BPMNEdge mapToBpmnEdge(List<PtsStatusWayPoint> ptsStatusWayPoints, SequenceFlow sequenceFlow,
			BPMNShape sourceTask, BPMNShape targetTask) {

		List<WayPoint> wayPoints = wayPointsMapper.mapStatusWayPointsToWayPoints(ptsStatusWayPoints,
				sourceTask.getBounds(), targetTask.getBounds());

		BPMNEdge edge = new BPMNEdge();
		edge.setId("bpmnEdge_" + sequenceFlow.getId());
		edge.setBpmnElement(sequenceFlow.getId());
		edge.setWaypoints(wayPoints);
		return edge;
	}
}