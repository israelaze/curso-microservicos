package com.devsuperior.hrworker.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.hrworker.dtos.WorkerGetDTO;
import com.devsuperior.hrworker.entities.Worker;
import com.devsuperior.hrworker.repositories.WorkerRepository;

@Service
public class WorkerServices {

	@Autowired
	private WorkerRepository repository;

	@Autowired
	private ModelMapper mapper;

	public List<WorkerGetDTO> findAll() {

		List<Worker> listWorker = repository.findAll();
		List<WorkerGetDTO> listWorkerDto = new ArrayList<WorkerGetDTO>();

		for (Worker worker : listWorker) {
			WorkerGetDTO getDto = new WorkerGetDTO();
			mapper.map(worker, getDto);

			listWorkerDto.add(getDto);

		}

		return listWorkerDto;
	}

	public WorkerGetDTO findById(Long id) {

		Worker result = repository.findById(id).get();

		if (result == null) {
			return null;
		} else {

			WorkerGetDTO workerDto = new WorkerGetDTO();
			mapper.map(result, workerDto);

			return workerDto;

		}

	}
}
