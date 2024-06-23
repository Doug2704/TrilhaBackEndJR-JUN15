package com.candido.trilhaBackEndJR_JUN15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.candido.trilhaBackEndJR_JUN15.entity.Task;
import com.candido.trilhaBackEndJR_JUN15.repository.TaskRepository;

@RestController
public class TaskController {
	@Autowired
	private TaskRepository taskRepository;

	@PostMapping("/task")
	public String saveTask(@RequestBody Task task) {
		try {
			taskRepository.save(task);
			return "Tarefa criada";
		} catch (Exception e) {
			return e.getMessage();
		}

	};
}
