package com.thinktank.pts.workflowservice.api.graphql;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinktank.pts.workflowservice.model.PtsType;
import com.thinktank.pts.workflowservice.service.PtsTyeService;
import com.thinktank.pts.workflowservice.service.StatusChangeService;

import graphql.kickstart.tools.GraphQLQueryResolver;

/**
 * 
 * @author hedfim
 * @since 2023-11-20
 *
 */
@Component
public class QueryResolver implements GraphQLQueryResolver {

	@Autowired
	private StatusChangeService statusChangeService;

	@Autowired
	private PtsTyeService ptsTyeService;

	public String getStatusChangeDiagramByTypeId(Long typeId) {
		return statusChangeService.getStatusChangeDiagramByType(typeId);
	}

	public String getStatusChangeDiagram(String type) {
		return statusChangeService.getStatusChangeDiagram(type);
	}

	public Optional<PtsType> getPtsTypeById(Long id) {
		return ptsTyeService.findById(id);
	}

	public Optional<PtsType> getPtsTypeByName(String name) {
		return ptsTyeService.findByName(name);
	}

	public List<PtsType> getAllPtsTypes() {
		return ptsTyeService.getAllPtsTypes();
	}
}