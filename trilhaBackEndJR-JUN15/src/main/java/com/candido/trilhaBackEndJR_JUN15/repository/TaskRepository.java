package com.candido.trilhaBackEndJR_JUN15.repository;

import java.util.List;
import java.util.Optional;

import com.candido.trilhaBackEndJR_JUN15.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.candido.trilhaBackEndJR_JUN15.entity.task.Status;
import com.candido.trilhaBackEndJR_JUN15.entity.task.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

//	public Task findByid(String Id);
	public Task findByName(String name);

	public List<Task> findAllByStatus(Status status);
	public List<Task> findAllByUser(User user);
	public Task findByUser(User user);
}
