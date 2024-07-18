package com.candido.trilhaBackEndJR_JUN15.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.candido.trilhaBackEndJR_JUN15.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

   // User findByUsername(String username);

    UserDetails findByUsername(String username);
}
