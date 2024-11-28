package com.thinktank.pts.workflowservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.workflowservice.model.PtsType;

@Repository
public interface PtsTypeRepository extends JpaRepository<PtsType, Long> {

	Optional<PtsType> findByName(String name);

	@Override
	Optional<PtsType> findById(Long id);

}
