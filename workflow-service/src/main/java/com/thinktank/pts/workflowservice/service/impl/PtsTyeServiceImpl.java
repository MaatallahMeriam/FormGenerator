package com.thinktank.pts.workflowservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.workflowservice.model.PtsType;
import com.thinktank.pts.workflowservice.repository.PtsTypeRepository;
import com.thinktank.pts.workflowservice.service.PtsTyeService;

@Service
public class PtsTyeServiceImpl implements PtsTyeService {

	@Autowired
	private PtsTypeRepository ptsTypeRepository;

	@Override
	public PtsType createPtsType(PtsType ptsType) {
		return ptsTypeRepository.save(ptsType);
	}

	@Override
	public PtsType updatePtsType(PtsType ptsType) {
		return ptsTypeRepository.save(ptsType);
	}

	@Override
	public void deletePtsType(Long id) {
		ptsTypeRepository.deleteById(id);
	}

	@Override
	public Optional<PtsType> findById(Long id) {
		return ptsTypeRepository.findById(id);
	}

	@Override
	public Optional<PtsType> findByName(String name) {
		return ptsTypeRepository.findByName(name);
	}

	@Override
	public List<PtsType> getAllPtsTypes() {
		return ptsTypeRepository.findAll();
	}

	@Override
	public PtsType getPtsTypeById(Long id) {
		Optional<PtsType> ptsTypeOptional = ptsTypeRepository.findById(id);
		return ptsTypeOptional.orElse(null); // Return the found PtsType or null if not found
	}

}
