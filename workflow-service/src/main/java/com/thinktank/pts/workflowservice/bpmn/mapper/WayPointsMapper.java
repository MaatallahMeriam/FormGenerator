package com.thinktank.pts.workflowservice.bpmn.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.thinktank.pts.workflowservice.bpmn.model.Bounds;
import com.thinktank.pts.workflowservice.bpmn.model.WayPoint;
import com.thinktank.pts.workflowservice.model.PtsStatusChange;
import com.thinktank.pts.workflowservice.model.PtsStatusWayPoint;

/**
 * Class mapper for WayPoints
 * 
 * @author hedfim
 * @since 2023-11-20
 *
 */
@Service
public class WayPointsMapper {

	/**
	 * maps ptsStatusWayPoint to Bounds
	 * 
	 * @param ptsStatusWayPoint
	 *            the status bounds to map
	 * @param index
	 *            the index of status
	 * @return Bounds
	 */
	public List<WayPoint> mapStatusWayPointsToWayPoints(List<PtsStatusWayPoint> ptsStatusWayPoints, Bounds sourceBounds,
			Bounds targetBounds) {

		List<WayPoint> wayPoints = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(ptsStatusWayPoints)) {
			ptsStatusWayPoints.forEach(wayPoint -> {
				wayPoints.add(new WayPoint(wayPoint.getPositionX(), wayPoint.getPositionY()));
			});
		} else {
			wayPoints.add(new WayPoint(sourceBounds.getX(), sourceBounds.getY()));
			wayPoints.add(new WayPoint(targetBounds.getX(), targetBounds.getY()));
		}

		return wayPoints;
	}

	/**
	 * maps bounds to PtsStatusWayPoint
	 * 
	 * @param bounds
	 *            bpmn bounds
	 * @param bpmnElementId
	 *            the element id
	 * @param ptsStatus
	 *            the status related to status bounds
	 * @return PtsStatusWayPoint
	 */
	public PtsStatusWayPoint mapWayPointsToStatusWayPoint(WayPoint wayPoint, String bpmnElementId,
			PtsStatusChange ptsStatusChange) {
		PtsStatusWayPoint ptsStatusWayPoint = new PtsStatusWayPoint();
		ptsStatusWayPoint.setBpmnElementId(bpmnElementId);
		ptsStatusWayPoint.setPositionX(wayPoint.getX());
		ptsStatusWayPoint.setPositionY(wayPoint.getY());
		ptsStatusWayPoint.setPtsStatusChange(ptsStatusChange);
		return ptsStatusWayPoint;
	}

}
