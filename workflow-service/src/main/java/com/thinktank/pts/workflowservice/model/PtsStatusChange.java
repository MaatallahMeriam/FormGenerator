package com.thinktank.pts.workflowservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "PTS_STATUS_CHANGE")
public class PtsStatusChange extends AbstractBaseMainTraceableEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "TICKET_ID", unique = true)
	private Long ticketId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FROM_PTS_STATUS_ID", referencedColumnName = "id", nullable = false)
	private PtsStatus fromPtsStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TO_PTS_STATUS_ID", referencedColumnName = "id", nullable = false)
	private PtsStatus toPtsStatus;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TYPE_ID", referencedColumnName = "id", nullable = false)
	private PtsType type;

	@Column(name = "DESCRIPTION")
	private String description;

	@OneToMany(mappedBy = "ptsStatusChange", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PtsStatusWayPoint> statusChangeWayPoints;

}
