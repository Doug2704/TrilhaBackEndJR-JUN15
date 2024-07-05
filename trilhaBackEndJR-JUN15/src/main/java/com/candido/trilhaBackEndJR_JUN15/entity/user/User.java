package com.candido.trilhaBackEndJR_JUN15.entity.user;

import java.util.List;

import com.candido.trilhaBackEndJR_JUN15.entity.task.Task;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference // essa anotação foi necessária para evitar um loop na consulta
    private List<Task> tasks;

    public User() {
    }

    public User(String id, String username, String password, List<Task> tasks) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tasks = tasks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
