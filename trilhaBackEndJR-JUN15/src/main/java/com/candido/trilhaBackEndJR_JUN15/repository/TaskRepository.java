package com.candido.trilhaBackEndJR_JUN15.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.candido.trilhaBackEndJR_JUN15.entity.task.Status;
import com.candido.trilhaBackEndJR_JUN15.entity.task.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

	public Optional<Task> findById(String Id);

	public Task findByName(String name);

	public List<Task> findAllByStatus(Status status);
}
