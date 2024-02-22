package com.blogapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

	   Optional<Role> findByName(String name);
}
