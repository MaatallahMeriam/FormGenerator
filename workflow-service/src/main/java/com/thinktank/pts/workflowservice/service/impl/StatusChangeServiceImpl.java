package com.thinktank.pts.workflowservice.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.apibase.graphql.exception.UnknownIDException;
import com.thinktank.pts.workflowservice.bpmn.mapper.BPMNBoundsMapper;
import com.thinktank.pts.workflowservice.bpmn.mapper.BPMNDiagramMapper;
import com.thinktank.pts.workflowservice.bpmn.mapper.BPMNEdgeMapper;
import com.thinktank.pts.workflowservice.bpmn.mapper.BPMNShapeMapper;
import com.thinktank.pts.workflowservice.bpmn.mapper.DefinitionsMapper;
import com.thinktank.pts.workflowservice.bpmn.mapper.ProcessMapper;
import com.thinktank.pts.workflowservice.bpmn.mapper.TaskEventMapper;
import com.thinktank.pts.workflowservice.bpmn.mapper.WayPointsMapper;
import com.thinktank.pts.workflowservice.bpmn.model.BPMNDiagram;
import com.thinktank.pts.workflowservice.bpmn.model.BPMNEdge;
import com.thinktank.pts.workflowservice.bpmn.model.BPMNShape;
import com.thinktank.pts.workflowservice.bpmn.model.Definitions;
import com.thinktank.pts.workflowservice.bpmn.model.SequenceFlow;
import com.thinktank.pts.workflowservice.bpmn.model.TaskEvent;
import com.thinktank.pts.workflowservice.bpmn.service.XmlParser;
import com.thinktank.pts.workflowservice.model.PtsStatus;
import com.thinktank.pts.workflowservice.model.PtsStatusBounds;
import com.thinktank.pts.workflowservice.model.PtsStatusChange;
import com.thinktank.pts.workflowservice.model.PtsStatusWayPoint;
import com.thinktank.pts.workflowservice.model.PtsType;
import com.thinktank.pts.workflowservice.repository.PtsStatusChangeRepository;
import com.thinktank.pts.workflowservice.repository.PtsStatusRepository;
import com.thinktank.pts.workflowservice.repository.StatusBoundsRepository;
import com.thinktank.pts.workflowservice.repository.StatusWayPointRepository;
import com.thinktank.pts.workflowservice.service.PtsTyeService;
import com.thinktank.pts.workflowservice.service.StatusChangeService;

import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation for status change
 * 
 * @author hedfim
 * @since 2023-11-03
 *
 */
@Service
@Slf4j
public class StatusChangeServiceImpl implements StatusChangeService {

	@Autowired
	private PtsStatusChangeRepository ptsStatusChangeRepository;

	@Autowired
	private PtsStatusRepository ptsStatusRepository;

	@Autowired
	private StatusWayPointRepository statusWayPointRepository;

	@Autowired
	private StatusBoundsRepository statusBoundsRepository;

	@Autowired
	private XmlParser xmlParser;

	@Autowired
	private DefinitionsMapper definitionsMapper;

	@Autowired
	private TaskEventMapper taskEventMapper;

	@Autowired
	private ProcessMapper processMapper;

	@Autowired
	private BPMNDiagramMapper bpmnDiagramMapper;

	@Autowired
	private BPMNShapeMapper bpmnShapeMapper;

	@Autowired
	private BPMNEdgeMapper bpmnEdgeMapper;

	@Autowired
	private BPMNBoundsMapper bpmnBoundsMapper;

	@Autowired
	private WayPointsMapper wayPointsMapper;

	@Autowired
	private PtsTyeService ptsTyeService;

	@Override
	public String getStatusChangeDiagramByType(Long typeId) {
		PtsType type = ptsTyeService.findById(typeId).orElseThrow(() -> new UnknownIDException(typeId));
		return buildBpmn(type);
	}

	@Override
	public String getStatusChangeDiagram(String name) {
		Optional<PtsType> type = ptsTyeService.findByName(name);
		if (type.isPresent()) {
			return buildBpmn(type.get());
		}
		return null;
	}

	@Override
	public String createOrUpdateStatus(String xmlString, Long typeId) {

		PtsType type = ptsTyeService.findById(typeId).orElseThrow(() -> new UnknownIDException(typeId));

		Definitions definitions = xmlParser.unmarshalToDefinition(xmlString);

		// Get all PtsStatus entities with type ticket
		List<PtsStatus> allPtsStatus = ptsStatusRepository.findByType(type);
		List<PtsStatusChange> statusChanges = ptsStatusChangeRepository.findByType(type);

		// Add status
		List<TaskEvent> tasks = definitions.getProcess().getTasks();
		List<TaskEvent> newTasks = tasks.stream().filter(task -> task.getStatus().equals("added")).toList();
		newTasks.stream().forEach(task -> {
			PtsStatus ptsStatus = taskEventMapper.mapTaskToStatus(task, type);
			ptsStatus = ptsStatusRepository.save(ptsStatus);

			Optional<BPMNShape> bpmnShape = definitions.getBpmnDiagram().getBpmnPlane().getBpmnShapes().stream()
					.filter(shape -> shape.getBpmnElement().equals(task.getId())).findFirst();
			PtsStatusBounds ptsStatusBounds = bpmnBoundsMapper.mapBpmnBoundsToStatusBounds(null,
					bpmnShape.get().getBounds(), "bpmnShape_" + task.getName(), ptsStatus);
			ptsStatusBounds = statusBoundsRepository.save(ptsStatusBounds);
		});

		// Modify a status
		List<TaskEvent> modifiedTasks = tasks.stream().filter(task -> task.getStatus().equals("modified")).toList();
		modifiedTasks.stream().forEach(task -> {
			Optional<PtsStatus> ptsStatusOpt = allPtsStatus.stream()
					.filter(status -> removeSpeacialCaracters(status.getName()).equals(task.getId())).findFirst();
			if (ptsStatusOpt.isPresent()) {
				PtsStatus ptsStatus = ptsStatusOpt.get();
				// If task name is changed update the name and BpmnElementId in way point
				if (!task.getName().equals(ptsStatus.getName())) {
					ptsStatus.setName(task.getName());
					ptsStatus = ptsStatusRepository.save(ptsStatus);
					List<PtsStatusWayPoint> ptsStatusWayPoints = statusWayPointRepository
							.findByBpmnElementIdContaining(task.getId());
					if (CollectionUtils.isNotEmpty(ptsStatusWayPoints)) {
						ptsStatusWayPoints.stream().forEach(wayPoint -> {
							wayPoint.setBpmnElementId(
									wayPoint.getBpmnElementId().replace(task.getId(), task.getName()));
							wayPoint = statusWayPointRepository.save(wayPoint);
						});
					}
				}

				Optional<BPMNShape> bpmnShape = definitions.getBpmnDiagram().getBpmnPlane().getBpmnShapes().stream()
						.filter(shape -> shape.getBpmnElement().equals(task.getId())).findFirst();
				PtsStatusBounds ptsStatusBounds = statusBoundsRepository.findByPtsStatus(ptsStatus);
				ptsStatusBounds = bpmnBoundsMapper.mapBpmnBoundsToStatusBounds(ptsStatusBounds,
						bpmnShape.get().getBounds(), "bpmnShape_" + task.getName(), ptsStatus);
				ptsStatusBounds = statusBoundsRepository.save(ptsStatusBounds);
			}

		});

		// Delete status
		List<PtsStatus> missingStatus = allPtsStatus.stream()
				.filter(status -> tasks.stream().noneMatch(task -> task.getName().equals(status.getName()))).toList();
		missingStatus.stream().forEach(status -> {
			ptsStatusRepository.delete(status);
		});

		// Add sequence flow
		List<SequenceFlow> sequenceFlows = definitions.getProcess().getSequenceFlows();
		List<SequenceFlow> newSequenceFlows = sequenceFlows.stream()
				.filter(sequenceFlow -> sequenceFlow.getStatus().equals("added")).toList();
		newSequenceFlows.stream().forEach(sequenceFlow -> {
			PtsStatusChange statusChange = new PtsStatusChange();
			Optional<PtsStatus> fromStatusOpt = allPtsStatus.stream()
					.filter(status -> removeSpeacialCaracters(status.getName()).equals(sequenceFlow.getSourceRef()))
					.findFirst();
			if (fromStatusOpt.isPresent()) {
				statusChange.setFromPtsStatus(fromStatusOpt.get());
			}
			Optional<PtsStatus> toStatusOpt = allPtsStatus.stream()
					.filter(status -> removeSpeacialCaracters(status.getName()).equals(sequenceFlow.getSourceRef()))
					.findFirst();
			if (toStatusOpt.isPresent()) {
				statusChange.setToPtsStatus(toStatusOpt.get());
			}

			statusChange.setType(type);
			statusChange = ptsStatusChangeRepository.save(statusChange);
			final PtsStatusChange finalStatusChange = statusChange;
			List<BPMNEdge> bpmnEdges = definitions.getBpmnDiagram().getBpmnPlane().getBpmnEdges().stream()
					.filter(edge -> edge.getBpmnElement().equals(sequenceFlow.getId())).toList();
			bpmnEdges.forEach(edge -> {

				edge.getWaypoints().forEach(wayPoint -> {
					PtsStatusWayPoint ptsStatusWayPoint = wayPointsMapper.mapWayPointsToStatusWayPoint(wayPoint,
							"bpmnEdge_" + sequenceFlow.getSourceRef() + "_" + sequenceFlow.getTargetRef(),
							finalStatusChange);
					statusWayPointRepository.save(ptsStatusWayPoint);
				});
			});
		});

		// Modify existing sequence flow
		List<SequenceFlow> oldSequenceFlows = sequenceFlows.stream()
				.filter(sequenceFlow -> sequenceFlow.getStatus().equals("modified")).toList();
		oldSequenceFlows.stream().forEach(sequenceFlow -> {
			PtsStatus fromStatus = null;

			PtsStatus toStatus = null;

			Optional<PtsStatus> fromStatusOpt = allPtsStatus.stream()
					.filter(status -> removeSpeacialCaracters(status.getName()).equals(sequenceFlow.getSourceRef()))
					.findFirst();
			if (fromStatusOpt.isPresent()) {
				fromStatus = fromStatusOpt.get();
			}
			Optional<PtsStatus> toStatusOpt = allPtsStatus.stream()
					.filter(status -> removeSpeacialCaracters(status.getName()).equals(sequenceFlow.getTargetRef()))
					.findFirst();
			if (toStatusOpt.isPresent()) {
				toStatus = toStatusOpt.get();
			}

			PtsStatusChange statusChange = ptsStatusChangeRepository
					.findByFromPtsStatusAndToPtsStatusAndType(fromStatus, toStatus, type);

			if (statusChange == null) {
				// delete modified status change
				String[] result = sequenceFlow.getId().split("_");
				PtsStatus removedFromStatus = null;
				PtsStatus removedToStatus = null;
				Optional<PtsStatus> removedFromStatusOpt = allPtsStatus.stream()
						.filter(status -> removeSpeacialCaracters(status.getName()).equals(result[0])).findFirst();
				if (removedFromStatusOpt.isPresent()) {
					removedFromStatus = removedFromStatusOpt.get();
				}
				Optional<PtsStatus> removedToStatusOpt = allPtsStatus.stream()
						.filter(status -> removeSpeacialCaracters(status.getName()).equals(result[1])).findFirst();
				if (removedToStatusOpt.isPresent()) {
					removedToStatus = removedToStatusOpt.get();
				}

				log.info("removedFromStatus={}, removedToStatus={}, type={}", removedFromStatus.getId(), removedToStatus.getId(), type.getId());
				PtsStatusChange changedStatusChange = ptsStatusChangeRepository
						.findByFromPtsStatusAndToPtsStatusAndType(removedFromStatus, removedToStatus, type);
				ptsStatusChangeRepository.delete(changedStatusChange);


				// Add new status change
				statusChange = new PtsStatusChange();
				statusChange.setFromPtsStatus(fromStatus);
				statusChange.setToPtsStatus(toStatus);
				statusChange.setType(type);
				statusChange = ptsStatusChangeRepository.save(statusChange);
			}

			// Save positions
			final PtsStatusChange statusChangeFinal = statusChange;
			statusWayPointRepository.deleteByBpmnElementId("bpmnEdge_" + sequenceFlow.getId());
			List<BPMNEdge> bpmnEdges = definitions.getBpmnDiagram().getBpmnPlane().getBpmnEdges().stream()
					.filter(edge -> edge.getBpmnElement().equalsIgnoreCase(sequenceFlow.getId())).toList();
			bpmnEdges.stream().forEach(edge -> {

				edge.getWaypoints().stream().forEach(wayPoint -> {
					PtsStatusWayPoint ptsStatusWayPoint = wayPointsMapper.mapWayPointsToStatusWayPoint(wayPoint,
							"bpmnEdge_" + sequenceFlow.getSourceRef() + "_" + sequenceFlow.getTargetRef(),
							statusChangeFinal);
					statusWayPointRepository.save(ptsStatusWayPoint);
				});
			});
		});

		// Delete missed status changes
		List<PtsStatusChange> missingStatusChanges = statusChanges.stream()
				.filter(statusChange -> sequenceFlows.stream()
						.noneMatch(sequenceFlow -> sequenceFlow.getSourceRef()
								.equals(removeSpeacialCaracters(statusChange.getFromPtsStatus().getName()))
								&& sequenceFlow.getTargetRef()
										.equals(removeSpeacialCaracters(statusChange.getToPtsStatus().getName()))))
				.toList();
		missingStatusChanges.stream().forEach(statusChnage -> {
			ptsStatusChangeRepository.delete(statusChnage);
		});

		return xmlParser.marshalToXML(definitions);

	}

	/**
	 * builds the bpmn diagram by the given type
	 * 
	 * @param type
	 *            the pts type
	 * @returnn string contain xml diagram
	 */
	private String buildBpmn(PtsType type) {
		List<PtsStatus> allPtsStatus = ptsStatusRepository.findByType(type);
		List<PtsStatusChange> statusChanges = ptsStatusChangeRepository.findByType(type);
		List<SequenceFlow> sequenceFlows;

		// Create tasks
		List<TaskEvent> tasks = allPtsStatus.stream().map(ptsStatus -> {
			return taskEventMapper.mapStatusToTask(ptsStatus, statusChanges);
		}).toList();

		// Create sequence flow
		List<String> allFlows = new ArrayList<>();
		for (TaskEvent taskEvent : tasks) {
			allFlows.addAll(new HashSet<>(taskEvent.getIncoming()));
			allFlows.addAll(new HashSet<>(taskEvent.getOutgoing()));
		}

		sequenceFlows = new HashSet<>(allFlows).stream().map(flow -> {
			String[] result = flow.split("_");
			return new SequenceFlow(flow, result[0], result[1], "old");
		}).toList();

		// Create shapes
		List<BPMNShape> shapes = allPtsStatus.stream().map(ptsStatus -> {
			// Create the Bounds object with the updated values
			PtsStatusBounds ptsStatusBounds = statusBoundsRepository.findByPtsStatus(ptsStatus);
			return bpmnShapeMapper.mapStatusChangeToBpmnShape(ptsStatus, ptsStatusBounds,
					allPtsStatus.indexOf(ptsStatus));
		}).toList();

		// Create egdes
		List<BPMNEdge> edges = sequenceFlows.stream().map(sequenceFlow -> {

			BPMNShape sourceTask = shapes.stream()
					.filter(shape -> shape.getBpmnElement().equalsIgnoreCase(sequenceFlow.getSourceRef())).findFirst()
					.get();

			BPMNShape targetTask = shapes.stream()
					.filter(shape -> shape.getBpmnElement().equalsIgnoreCase(sequenceFlow.getTargetRef())).findFirst()
					.get();

			Optional<PtsStatusChange> statusChange = statusChanges.stream()
					.filter(transition -> removeSpeacialCaracters(transition.getFromPtsStatus().getName())
							.equalsIgnoreCase(sequenceFlow.getSourceRef())
							&& removeSpeacialCaracters(transition.getToPtsStatus().getName())
									.equalsIgnoreCase(sequenceFlow.getTargetRef()))
					.findFirst();

			List<PtsStatusWayPoint> ptsStatusWayPoints = null;

			if (statusChange.isPresent()) {
				ptsStatusWayPoints = statusWayPointRepository.findByPtsStatusChange(statusChange.get());
			}

			return bpmnEdgeMapper.mapToBpmnEdge(ptsStatusWayPoints, sequenceFlow, sourceTask, targetTask);
		}).toList();

		// Create BPMN diagram
		com.thinktank.pts.workflowservice.bpmn.model.Process process = processMapper.create(tasks, sequenceFlows);
		BPMNDiagram bpmnDiagram = bpmnDiagramMapper.create(shapes, edges);
		Definitions definitions = definitionsMapper.create(bpmnDiagram, process);
		return xmlParser.marshalToXML(definitions);
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
