package com.candido.trilhaBackEndJR_JUN15.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.candido.trilhaBackEndJR_JUN15.entity.Task;
import com.candido.trilhaBackEndJR_JUN15.repository.TaskRepository;

@RestController
public class TaskController {
	@Autowired
	private TaskRepository taskRepository;

	@PostMapping("/task/save")
	public String saveTask(@RequestBody Task task) {
		try {
			Task existingTaskWithId = taskRepository.findById(task.getId());
			Task existingTaskWithName = taskRepository.findByName(task.getName());
			if (existingTaskWithId != null) {
				return "Uma tarefa com o id (" + task.getId() + ") já existe";
			} else if (existingTaskWithName != null) {
				return "Já existe uma tarefa com esse nome";
			} else {
				taskRepository.save(task);
				return "Tarefa salva";
			}

		} catch (Exception e) {
			return "erro ao criar tarefa: " + e.getMessage();
		}

	}

	@GetMapping("/task/id/{id}")
	public Task findById(@PathVariable Long id) {
		try {
			Task task = taskRepository.findById(id);
			if (task == null) {
				return null;
			}
			return task;
		} catch (Exception e) {
			throw new RuntimeException("erro ao buscar tarefa: ", e);
		}

	}

	@GetMapping("/task/name/{name}")
	public Task findByName(@PathVariable String name) {
		try {
			Task task = taskRepository.findByName(name);
			if (task == null) {
				return null;
			}
			return task;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar tarefa: ", e);
		}
	}

	@GetMapping("/task")
	public List<Task> findAll() {
		try {
			return taskRepository.findAll();
		} catch (Exception e) {
			throw new RuntimeException("erro ao buscar tarefa: ", e);
		}
	}

	@PostMapping("/task/update/{id}")
	public String updateTaskById(@RequestBody Task task) {
		try {
			taskRepository.save(task);
			return "Tarefa atualizada";
		} catch (Exception e) {
			return "erro ao criar tarefa: " + e.getMessage();
		}
	}
};