package com.candido.trilhaBackEndJR_JUN15.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.candido.trilhaBackEndJR_JUN15.entity.task.Status;
import com.candido.trilhaBackEndJR_JUN15.entity.task.Task;
import com.candido.trilhaBackEndJR_JUN15.repository.TaskRepository;

@RestController
public class TaskController {
	@Autowired
	private TaskRepository taskRepository;

	@PostMapping("/task/save")
	public String saveTask(@RequestBody Task task) {
		try {
			Task existsByName = taskRepository.findByName(task.getName());
			if (existsByName != null && existsByName.getName().equals(task.getName())) {
				return "Já existe uma tarefa com esse nome";
			}

			taskRepository.save(task);
			return "Tarefa salva";

		} catch (Exception e) {
			return "Erro ao criar tarefa: " + e.getMessage();
		}

	}

	@GetMapping("/task/id/{id}")
	public Optional<Task> findById(@PathVariable String id) {
		try {
			Optional<Task> task = taskRepository.findById(id);
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

	@GetMapping("/task/status/{status}")
	public List<Task> findAllByStatus(@PathVariable Status status) {
		try {
			List<Task> tasks = taskRepository.findAllByStatus(status);
			return tasks;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao consultar tarefas por status: " + status, e);
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

	@PostMapping("task/delete/{id}")
	public String deleteTaskById(@PathVariable String id) {
		Optional<Task> task = taskRepository.findById(id);
		try {
			if (task == null) {
				return "Tarefa inexistente";
			}
			taskRepository.deleteById(id);
			return "Tarefa apagada";
		} catch (Exception e) {
			return "erro ao apagar tarefa: " + e.getMessage();
		}
	}

};