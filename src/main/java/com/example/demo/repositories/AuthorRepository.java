package com.example.demo.repositories;

import com.example.demo.entities.Author;
import org.springframework.data.repository.CrudRepository;


public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author getAuthorById(int id);
}
