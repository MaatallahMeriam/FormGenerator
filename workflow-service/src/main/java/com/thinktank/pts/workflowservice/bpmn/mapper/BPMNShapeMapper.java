package com.thinktank.pts.workflowservice.bpmn.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.workflowservice.bpmn.model.BPMNShape;
import com.thinktank.pts.workflowservice.bpmn.model.Bounds;
import com.thinktank.pts.workflowservice.model.PtsStatus;
import com.thinktank.pts.workflowservice.model.PtsStatusBounds;

/**
 * Class mapper for BpmnShape
 * 
 * @author hedfim
 * @since 2023-11-20
 *
 */
@Service
public class BPMNShapeMapper {

	@Autowired
	private BPMNBoundsMapper bpmnBoundsMapper;

	/**
	 * maps status change to bpmn shape
	 * 
	 * @param ptsStatus
	 *            status
	 * @param ptsStatusBounds
	 *            status bounds
	 * @param index
	 *            index of status
	 * @return BPMNShape
	 */
	public BPMNShape mapStatusChangeToBpmnShape(PtsStatus ptsStatus, PtsStatusBounds ptsStatusBounds, int index) {

		Bounds bounds = bpmnBoundsMapper.mapStatusBoundsToBpmnBounds(ptsStatusBounds, index);

		BPMNShape shape = new BPMNShape();
		shape.setId("bpmnShape_" + removeSpeacialCaracters(ptsStatus.getName()));
		shape.setBounds(bounds);
		shape.setBpmnElement(removeSpeacialCaracters(ptsStatus.getName()));

		return shape;
	}

	/**
	 * removes special caracters from the given string
	 * 
	 * @param originalString
	 *            the original string
	 * @return string after removing special caracters
	 */
	private String removeSpeacialCaracters(String originalString) {
		return originalString.replaceAll("[^a-zA-Z0-9]", "");
	}

}
