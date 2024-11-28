package com.thinktank.pts.workflowservice.bpmn.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thinktank.pts.workflowservice.bpmn.model.Process;
import com.thinktank.pts.workflowservice.bpmn.model.SequenceFlow;
import com.thinktank.pts.workflowservice.bpmn.model.TaskEvent;

/**
 * Class mapper for Bpmn process
 * 
 * @author hedfim
 * @since 2023-11-20
 *
 */
@Service
public class ProcessMapper {

	/**
	 * creates process by the given tasks and flows
	 * 
	 * @param tasks
	 *            list of bpmn tasks
	 * @param sequenceFlows
	 *            list of bpmn sequence flows
	 * @return Process the bpmn process
	 */
	public Process create(List<TaskEvent> tasks, List<SequenceFlow> sequenceFlows) {
		Process process = new Process();
		process.setId("status_workflow_process");
		process.setExecutable(false);
		process.setTasks(tasks);
		process.setSequenceFlows(sequenceFlows);
		return process;
	}

}
