package com.thinktank.pts.workflowservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.workflowservice.model.PtsStatus;
import com.thinktank.pts.workflowservice.model.PtsType;

@Repository
public interface PtsStatusRepository extends JpaRepository<PtsStatus, Long> {

	/**
	 * returns a list of status by the given type and subType
	 * 
	 * @param type
	 *            the object type
	 * @return List<PtsStatus> list of status
	 */
	public List<PtsStatus> findByType(PtsType type);

}
