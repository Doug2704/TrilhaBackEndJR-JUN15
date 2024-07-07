package com.candido.trilhaBackEndJR_JUN15.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.candido.trilhaBackEndJR_JUN15.entity.task.Status;
import com.candido.trilhaBackEndJR_JUN15.entity.task.Task;
import com.candido.trilhaBackEndJR_JUN15.entity.user.User;
import com.candido.trilhaBackEndJR_JUN15.repository.TaskRepository;
import com.candido.trilhaBackEndJR_JUN15.repository.UserRepository;

@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/task/save/{user_id}")
    public String saveTask(@PathVariable String user_id, @RequestBody Task task) {
        try {
            Optional<User> user = userRepository.findById(user_id);
            if (user.isEmpty()) {
                return "Usuário não encontrado";
            }
            task.setUser(user.orElse(null));
            Task existsByName = taskRepository.findByName(task.getName());
            //criar lógica para validar se tem apenas no usuario e nao em todos
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
            throw new RuntimeException("Erro ao consultar tarefas: " + status, e);
        }
    }

    @GetMapping("/task/user/{username}")
    public List<Task> findAllByUser(@PathVariable String username) {
        try {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                return null;
            }
            return user.getTasks();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar tarefas", e);
        }
    }

    @PostMapping("/task/update/{id}")
    public String updateTaskById(@PathVariable String id, @RequestBody Task task) {
        try {
            task.setId(id);
            taskRepository.save(task);
            return "Tarefa atualizada";
        } catch (Exception e) {
            return "erro ao atualizar tarefa: " + e.getMessage();
        }
    }

    @DeleteMapping("/task/delete/{id}")
    public String deleteTaskById(@PathVariable String id) {
        Optional<Task> task = taskRepository.findById(id);
        try {
            if (task.isEmpty()) {
                return "Tarefa inexistente";
            }
            taskRepository.deleteById(id);
            return "Tarefa apagada";
        } catch (Exception e) {
            return "erro ao apagar tarefa: " + e.getMessage();
        }
    }

    @DeleteMapping("task/deleteAll")
    public String deleteAllTasks() {
        try {
            taskRepository.deleteAll();
            return "Todos as tarefas foram apagadas";
        } catch (Exception e) {
            return "erro ao apagar tarefas: " + e.getMessage();
        }
    }


};