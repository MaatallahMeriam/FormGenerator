package com.thinktank.pts.workflowservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @since 2023-11-10
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STATUS_WAY_POINT")
public class PtsStatusWayPoint extends AbstractBaseMainTraceableEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NonNull
	@Column(name = "BPMN_ELEMENT_ID", nullable = false)
	private String bpmnElementId;

	@NonNull
	@Column(name = "POSITION_X", nullable = false)
	private Integer positionX;

	@NonNull
	@Column(name = "POSITION_Y", nullable = false)
	private Integer positionY;

	@ManyToOne
	@JoinColumn(name = "PTS_STATUS_CHANGE_ID", referencedColumnName = "id", nullable = false)
	private PtsStatusChange ptsStatusChange;
}
