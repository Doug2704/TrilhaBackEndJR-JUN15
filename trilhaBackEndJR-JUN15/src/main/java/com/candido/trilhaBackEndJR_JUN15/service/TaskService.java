package com.candido.trilhaBackEndJR_JUN15.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candido.trilhaBackEndJR_JUN15.entity.Task;
import com.candido.trilhaBackEndJR_JUN15.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public Task findById(Long id) {
		return taskRepository.findById(id);
	};

	public Task findByName(String name) {
		return taskRepository.findByName(name);
	}
	public List<Task> findAll() {
		return taskRepository.findAll();
	}

}
