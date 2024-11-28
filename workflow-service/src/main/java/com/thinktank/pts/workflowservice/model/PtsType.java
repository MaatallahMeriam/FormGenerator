package com.thinktank.pts.workflowservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thinktank.pts.apibase.model.base.AbstractBaseMainTraceableEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PTS_TYPE")
public class PtsType extends AbstractBaseMainTraceableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NonNull
	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "TYPE", nullable = false)
	private String type;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "parent_type_id")
	private Long parentTypeId;

	@Column(name = "TICKET_ID", unique = true)
	private Long ticketId;

	@Column(name = "MODULE")
	private Long module;

	@Column(name = "table_name", length = 100)
	private String tableName;

	@Column(name = "static_table", length = 100)
	private String staticTable;

	@Column(name = "admin_images", nullable = true)
	private int adminImages;

	@Column(name = "billable")
	private Boolean billable;

	@Column(name = "standalone")
	private Boolean standalone;

}
