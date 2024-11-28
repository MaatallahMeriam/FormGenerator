package com.thinktank.pts.workflowservice.api.graphql.inputs;

import com.thinktank.pts.apibase.graphql.AbstractGraphQlArgumentAwareBaseInput;
import com.thinktank.pts.apibase.model.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor

public class PtsTypeInput extends AbstractGraphQlArgumentAwareBaseInput implements BaseEntity {
	private Long ticketId;
	private String name;
	private String type;
	private String description;
	private Long parentTypeId;
	private int adminImages;
	private String tableName;
	private Boolean standalone;
	private Boolean billable;
	private String staticTable;
	private Long module;

	// Getter and Setter for 'name'
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Getter and Setter for 'description'
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// Getter and Setter for 'parentTypeId'
	public Long getParentTypeId() {
		return parentTypeId;
	}

	public void setParentTypeId(Long parentTypeId) {
		this.parentTypeId = parentTypeId;
	}

	// Getter and Setter for 'ticketId'
	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	// Getter and Setter for 'module'
	public Long getModule() {
		return module;
	}

	public void setModule(Long module) {
		this.module = module;
	}

	// Getter and Setter for 'tableName'
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	// Getter and Setter for 'staticTable'
	public String getStaticTable() {
		return staticTable;
	}

	public void setStaticTable(String staticTable) {
		this.staticTable = staticTable;
	}

	// Getter and Setter for 'adminImages'
	public Integer getAdminImages() {
		return adminImages;
	}

	public void setAdminImages(Integer adminImages) {
		this.adminImages = adminImages;
	}

	// Getter and Setter for 'billable'
	public Boolean getBillable() {
		return billable;
	}

	public void setBillable(Boolean billable) {
		this.billable = billable;
	}

	// Getter and Setter for 'standalone'
	public Boolean getStandalone() {
		return standalone;
	}

	public void setStandalone(Boolean standalone) {
		this.standalone = standalone;
	}

	// Getter and Setter for 'standalone'
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}