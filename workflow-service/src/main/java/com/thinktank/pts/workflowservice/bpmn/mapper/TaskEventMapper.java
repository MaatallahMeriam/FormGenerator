package com.thinktank.pts.workflowservice.bpmn.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thinktank.pts.workflowservice.bpmn.model.TaskEvent;
import com.thinktank.pts.workflowservice.model.PtsStatus;
import com.thinktank.pts.workflowservice.model.PtsStatusChange;
import com.thinktank.pts.workflowservice.model.PtsType;

/**
 * class mapper TaskEvent
 * 
 * @author hedfim
 * @since 2023-11-20
 *
 */
@Service
public class TaskEventMapper {

	/**
	 * maps status change entity to bpmn task
	 * 
	 * @param ptsStatus
	 *            the status to map
	 * @param statusChanges
	 *            the status changes related to the given status
	 * @return the task after mapping
	 */
	public TaskEvent mapStatusToTask(PtsStatus ptsStatus, List<PtsStatusChange> statusChanges) {
		List<String> incoming = statusChanges.stream()
				.filter(statusChange -> statusChange.getToPtsStatus().equals(ptsStatus))
				.map(statusChange -> String.join("_",
						removeSpeacialCaracters(statusChange.getFromPtsStatus().getName()),
						removeSpeacialCaracters(statusChange.getToPtsStatus().getName())))
				.toList();
		List<String> outgoing = statusChanges.stream()
				.filter(statusChange -> statusChange.getFromPtsStatus().equals(ptsStatus))
				.map(statusChange -> String.join("_",
						removeSpeacialCaracters(statusChange.getFromPtsStatus().getName()),
						removeSpeacialCaracters(statusChange.getToPtsStatus().getName())))
				.toList();

		TaskEvent task = new TaskEvent();
		task.setId(removeSpeacialCaracters(ptsStatus.getName()));
		task.setIncoming(incoming);
		task.setOutgoing(outgoing);
		task.setName(ptsStatus.getName());
		task.setStatus("old");
		return task;
	}

	/**
	 * maps bpmn task to PtsStatus
	 * 
	 * @param task
	 *            bpmn task
	 * @return PtsStatus
	 */
	public PtsStatus mapTaskToStatus(TaskEvent task, PtsType type) {
		PtsStatus ptsStatus = new PtsStatus();
		ptsStatus.setName(task.getName());
		ptsStatus.setType(type);
		return ptsStatus;

	}

	/**
	 * removes special caracters from the given string
	 * 
	 * @param originalString
	 *            the original string
	 * @return string after removing special caracters
	 */
	private String removeSpeacialCaracters(String originalString) {
		return originalString.replaceAll("[^a-zA-Z0-9]", "");
	}

}
