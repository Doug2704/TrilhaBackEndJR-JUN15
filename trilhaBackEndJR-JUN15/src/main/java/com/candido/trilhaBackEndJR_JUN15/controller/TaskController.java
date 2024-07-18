package com.candido.trilhaBackEndJR_JUN15.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            List<Task> tasks = taskRepository.findAllByUser(user.get());
            for (Task t : tasks) {
                if (task.getName().equals(t.getName())) {
                    return "Já existe uma tarefa com esse nome";
                }
            }

            task.setUser(user.orElse(null));
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
            if (task.isEmpty()) {
                return null;
            }
            return task;
        } catch (Exception e) {
            throw new RuntimeException("erro ao buscar tarefa: ", e);
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
            return taskRepository.findAllByStatus(status);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar tarefas: " + status, e);
        }
    }

    @GetMapping("/task/user/{username}")
    public ResponseEntity findAllByUser(@PathVariable String username) {
        try {
            User user = (User) userRepository.findByUsername(username);
            if (user == null) {
                return new ResponseEntity<>("Usuário inexistente", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(user.getTasks(), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar tarefas", e);
        }
    }

    @PutMapping("/task/update/{id}")
    public String updateTaskById(@PathVariable String id, @RequestBody Task task) {
        try {
            Optional<Task> retrievedTaskById = findById(id);
            if (retrievedTaskById == null) {
                return "Tarefa inexistente";
            }
            retrievedTaskById.get().setName(task.getName());
            retrievedTaskById.get().setStatus(task.getStatus());
            // retrievedTaskById.setUser(task.getUser());
            taskRepository.save(retrievedTaskById.get());

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