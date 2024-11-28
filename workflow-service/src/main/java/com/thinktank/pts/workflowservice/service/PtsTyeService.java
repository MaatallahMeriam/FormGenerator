package com.thinktank.pts.workflowservice.service;

import java.util.List;
import java.util.Optional;

import com.thinktank.pts.workflowservice.model.PtsType;

/**
 * service for pts type
 * 
 * @author hedfim
 * @since 2023-12-04
 *
 */
public interface PtsTyeService {

	// creation
	PtsType createPtsType(PtsType ptsType);

	public Optional<PtsType> findById(Long id);

	public Optional<PtsType> findByName(String name);

	PtsType updatePtsType(PtsType ptsType);

	void deletePtsType(Long id);

	List<PtsType> getAllPtsTypes();

	PtsType getPtsTypeById(Long id);

}
