package com.candido.trilhaBackEndJR_JUN15.entity.task;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.candido.trilhaBackEndJR_JUN15.entity.user.User;

@Entity
@Table(name = "tb_task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private String name;

	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToOne
	private User user;

	public Task() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
