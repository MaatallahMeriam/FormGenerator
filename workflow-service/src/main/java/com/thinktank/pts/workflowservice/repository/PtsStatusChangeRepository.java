package com.thinktank.pts.workflowservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.workflowservice.model.PtsStatus;
import com.thinktank.pts.workflowservice.model.PtsStatusChange;
import com.thinktank.pts.workflowservice.model.PtsType;

@Repository
public interface PtsStatusChangeRepository extends JpaRepository<PtsStatusChange, Long> {

	/**
	 * returns a list of status change by the given type and subType
	 * 
	 * @param type
	 *            the object type
	 * @return List<PtsStatusChange> list of status
	 */
	public List<PtsStatusChange> findByType(PtsType type);

	/**
	 * 
	 * @param ptsStatus
	 * @param type
	 * @param subType
	 * @return
	 */
	public List<PtsStatusChange> findByFromPtsStatusAndType(PtsStatus ptsStatus, PtsType type);

	/**
	 * 
	 * @param ptsStatus
	 * @param type
	 * @param subType
	 * @return
	 */
	public List<PtsStatusChange> findByToPtsStatusAndType(PtsStatus ptsStatus, PtsType type);

	/**
	 * 
	 * @param ptsStatus
	 * @param type
	 * @param subType
	 * @return
	 */
	public PtsStatusChange findByFromPtsStatusAndToPtsStatusAndType(PtsStatus from, PtsStatus to, PtsType type);

	@Override
	@Transactional
	public void delete(PtsStatusChange statusChange);

}
