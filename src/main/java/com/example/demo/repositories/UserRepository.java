package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.data.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
