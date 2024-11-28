package com.thinktank.pts.workflowservice.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author hedfim
 * @since 2023-11-01
 *
 */
@RestController
@CrossOrigin
@Tag(name = "pts_status", description = "API for CRUD operations on pts status.")
@Slf4j
@Transactional
public class PtsStatusController {

	@GetMapping("/download")
	public ResponseEntity<Resource> getStatusWorkflowDiagram() {
		Resource fileResource = new ClassPathResource("example.bpmn");
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION).body(fileResource);

	}

}
