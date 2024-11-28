package com.thinktank.pts.workflowservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.workflowservice.model.PtsStatusChange;
import com.thinktank.pts.workflowservice.model.PtsStatusWayPoint;

@Repository
public interface StatusWayPointRepository extends JpaRepository<PtsStatusWayPoint, Long> {

	/**
	 * 
	 * @param bpmnElementI
	 * @return
	 */
	public List<PtsStatusWayPoint> findByBpmnElementId(String bpmnElementI);

	/**
	 * 
	 * @param bpmnElementI
	 * @return
	 */
	public List<PtsStatusWayPoint> findByBpmnElementIdContaining(String bpmnElementId);

	/**
	 * 
	 * @param bpmnElementI
	 * @return
	 */
	public void deleteByBpmnElementId(String bpmnElementI);

	/**
	 * 
	 * @param bpmnElementI
	 * @return
	 */
	public List<PtsStatusWayPoint> findByPtsStatusChange(PtsStatusChange ptsStatusChange);

}
