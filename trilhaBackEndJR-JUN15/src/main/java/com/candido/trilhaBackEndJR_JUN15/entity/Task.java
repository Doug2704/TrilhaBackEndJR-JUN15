package com.candido.trilhaBackEndJR_JUN15.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_task")
public class Task {

	@Id
	private int id;
	private String name;
	private Status status;

	public Task() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public void setStatus(String email) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Task{" + "id=" + id + ", name='" + name + '\'' + ", status='" + status + '\'' + '}';
	}
}
