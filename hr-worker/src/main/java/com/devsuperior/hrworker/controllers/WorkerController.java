package com.devsuperior.hrworker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.hrworker.dtos.WorkerGetDTO;
import com.devsuperior.hrworker.exceptions.ServiceException;
import com.devsuperior.hrworker.services.WorkerServices;

@RestController
@RequestMapping(value = "/workers")
public class WorkerController {

	@Autowired
	private WorkerServices services;

	@GetMapping
	public ResponseEntity<List<WorkerGetDTO>> findAll() {

		try {

			List<WorkerGetDTO> listWorkerDto = services.findAll();
			return ResponseEntity.ok(listWorkerDto);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<WorkerGetDTO> findById(@PathVariable Long id) {

		try {

			WorkerGetDTO workerDto = services.findById(id);
			return ResponseEntity.ok().body(workerDto);

		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}
}
