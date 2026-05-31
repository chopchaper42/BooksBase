package com.example.demo.crud;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.data.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
