package com.thinktank.pts.workflowservice.api.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinktank.pts.workflowservice.api.graphql.inputs.PtsTypeInput;
import com.thinktank.pts.workflowservice.model.PtsType;
import com.thinktank.pts.workflowservice.service.PtsTyeService;
import com.thinktank.pts.workflowservice.service.StatusChangeService;

import graphql.kickstart.tools.GraphQLMutationResolver;

/**
 * 
 * @author hedfim
 * @since 2023-11-20
 *
 */
@Component
public class Mutation implements GraphQLMutationResolver {

	@Autowired
	private StatusChangeService statusChangeService;

	@Autowired
	private PtsTyeService ptsTypeService;

	/**
	 * Creates or updates status and status changes
	 * 
	 * @param xmlString
	 *            the xml bpmn diagram
	 * @return string contain the xml file after update
	 */
	public String createOrUpdateStatus(String xmlString, Long typeId) {
		return statusChangeService.createOrUpdateStatus(xmlString, typeId);
	}

	public PtsType createPtsType(PtsTypeInput input) {
		PtsType result = new PtsType();

		result.setName(input.getName());
		result.setDescription(input.getDescription());
		result.setModule(input.getModule());
		result.setType(input.getType());
		result.setAdminImages(input.getAdminImages());
		result.setBillable(input.getBillable());
		result.setStandalone(input.getStandalone());
		result.setParentTypeId(input.getParentTypeId());
		result.setStaticTable(input.getStaticTable());
		result.setTableName(input.getTableName());
		result.setTicketId(input.getTicketId());

		return ptsTypeService.createPtsType(result);

	}

	public PtsType updatePtsType(Long id, PtsTypeInput input) {
		// Fetch the existing PtsType by ID
		PtsType existingType = ptsTypeService.getPtsTypeById(id);
		if (existingType == null) {
			throw new RuntimeException("PtsType not found with ID: " + id);
		}

		// Update the fields with the new input
		existingType.setName(input.getName());
		existingType.setDescription(input.getDescription());
		existingType.setModule(input.getModule());
		existingType.setType(input.getType());
		existingType.setAdminImages(input.getAdminImages());
		existingType.setBillable(input.getBillable());
		existingType.setStandalone(input.getStandalone());
		existingType.setParentTypeId(input.getParentTypeId());
		existingType.setStaticTable(input.getStaticTable());
		existingType.setTableName(input.getTableName());
		existingType.setTicketId(input.getTicketId());

		// Save the updated entity
		return ptsTypeService.updatePtsType(existingType);
	}

	public Boolean deletePtsType(Long id) {
		// Fetch the existing PtsType by ID
		PtsType existingType = ptsTypeService.getPtsTypeById(id);
		if (existingType == null) {
			throw new RuntimeException("PtsType not found with ID: " + id);
		}

		// Delete the PtsType
		ptsTypeService.deletePtsType(id);
		return true;
	}
}
