package com.thinktank.pts.workflowservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.thinktank.pts.apibase.model.base.AbstractBaseMainTraceableEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * 
 * @author hedfim
 * @since 2023-11-01
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PTS_STATUS")
public class PtsStatus extends AbstractBaseMainTraceableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "TICKET_ID", unique = true)
	private Long ticketId;

	@NonNull
	@Column(name = "NAME", nullable = false)
	private String name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TYPE_ID", referencedColumnName = "id", nullable = false)
	private PtsType type;

	@Column(name = "DESCRIPTION")
	private String description;

	@OneToMany(mappedBy = "fromPtsStatus", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PtsStatusChange> fromStatusChanges;

	@OneToMany(mappedBy = "toPtsStatus", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PtsStatusChange> toStatusChanges;

	@OneToMany(mappedBy = "ptsStatus", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PtsStatusBounds> statusBounds;

	@Column(name = "IS_START_STATUS")
	private Boolean isStartStatus;

}
