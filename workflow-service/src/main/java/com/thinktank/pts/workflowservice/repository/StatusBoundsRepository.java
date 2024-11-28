package com.thinktank.pts.workflowservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.workflowservice.model.PtsStatus;
import com.thinktank.pts.workflowservice.model.PtsStatusBounds;

@Repository
public interface StatusBoundsRepository extends JpaRepository<PtsStatusBounds, Long> {

	/**
	 * 
	 * @param bpmnElementI
	 * @param type
	 * @param subType
	 * @return
	 */
	public PtsStatusBounds findByBpmnElementId(String bpmnElementI);

	/**
	 * 
	 * @param ptsStatus
	 * @return
	 */
	public PtsStatusBounds findByPtsStatus(PtsStatus ptsStatus);

}
