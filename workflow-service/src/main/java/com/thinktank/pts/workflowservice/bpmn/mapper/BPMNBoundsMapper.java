package com.thinktank.pts.workflowservice.bpmn.mapper;

import org.springframework.stereotype.Service;

import com.thinktank.pts.workflowservice.bpmn.model.Bounds;
import com.thinktank.pts.workflowservice.model.PtsStatus;
import com.thinktank.pts.workflowservice.model.PtsStatusBounds;

/**
 * Class mapper for BpmnBounds
 * 
 * @author hedfim
 * @since 2023-11-20
 *
 */
@Service
public class BPMNBoundsMapper {

	/**
	 * maps ptsStatusBounds to Bounds
	 * 
	 * @param ptsStatusBounds
	 *            the status bounds to map
	 * @param index
	 *            the index of status
	 * @return Bounds
	 */
	public Bounds mapStatusBoundsToBpmnBounds(PtsStatusBounds ptsStatusBounds, int index) {

		Bounds bounds = new Bounds();
		if (ptsStatusBounds != null) {
			bounds.setHeight(ptsStatusBounds.getHeight());
			bounds.setWidth(ptsStatusBounds.getWidth());
			bounds.setX(ptsStatusBounds.getPositionX());
			bounds.setY(ptsStatusBounds.getPositionY());
		} else {
			// by default set those values
			bounds.setHeight(80);
			bounds.setWidth(100);
			bounds.setX(280 + (120 * index));
			bounds.setY(80 + (120 * index));
		}

		return bounds;
	}

	/**
	 * maps bounds to PtsStatusBounds
	 * 
	 * @param bounds
	 *            bpmn bounds
	 * @param bpmnElementId
	 *            the element id
	 * @param ptsStatus
	 *            the status related to status bounds
	 * @return PtsStatusBounds
	 */
	public PtsStatusBounds mapBpmnBoundsToStatusBounds(PtsStatusBounds ptsStatusBounds, Bounds bounds,
			String bpmnElementId, PtsStatus ptsStatus) {
		ptsStatusBounds = ptsStatusBounds == null ? new PtsStatusBounds() : ptsStatusBounds;
		ptsStatusBounds.setBpmnElementId(bpmnElementId);
		ptsStatusBounds.setHeight(bounds.getHeight());
		ptsStatusBounds.setWidth(bounds.getWidth());
		ptsStatusBounds.setPositionX(bounds.getX());
		ptsStatusBounds.setPositionY(bounds.getY());
		ptsStatusBounds.setPtsStatus(ptsStatus);
		return ptsStatusBounds;
	}

}
