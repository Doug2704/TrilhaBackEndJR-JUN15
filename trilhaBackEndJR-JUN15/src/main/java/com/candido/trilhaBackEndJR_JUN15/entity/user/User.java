package com.candido.trilhaBackEndJR_JUN15.entity.user;

import java.util.Collection;
import java.util.List;

import com.candido.trilhaBackEndJR_JUN15.entity.task.Task;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "tb_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false) //email and username are same, refactor after
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference // essa anotação foi necessária para evitar um loop na consulta
    private List<Task> tasks;

    //@Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getTypeRole()));
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
