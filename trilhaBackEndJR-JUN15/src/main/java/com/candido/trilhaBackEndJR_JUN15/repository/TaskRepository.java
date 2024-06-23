package com.candido.trilhaBackEndJR_JUN15.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.candido.trilhaBackEndJR_JUN15.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	public Task findById(Long Id);

	public Task findByName(String name);
}
