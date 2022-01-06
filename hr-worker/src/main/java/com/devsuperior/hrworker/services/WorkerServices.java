package com.devsuperior.hrworker.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.hrworker.dtos.WorkerGetDTO;
import com.devsuperior.hrworker.entities.Worker;
import com.devsuperior.hrworker.exceptions.NotFoundException;
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

		Optional<Worker> result = repository.findById(id);

		if (result == null || result.isEmpty()) {
			throw new NotFoundException("Entity not found!");
		} else {

			Worker worker = result.get();

			WorkerGetDTO workerDto = new WorkerGetDTO();
			// mapper.map(worker, workerDto);

			workerDto.setId(worker.getId());
			workerDto.setName(worker.getName());
			workerDto.setDailyIncome(worker.getDailyIncome());
			return workerDto;

		}

	}
}
