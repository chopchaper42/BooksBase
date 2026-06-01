package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Author;
import com.example.demo.entities.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findBookByTitleContainingIgnoreCase(String title);

    Book findBookById(long id);

    @Query("select b from Book b order by RANDOM() limit 3")
    List<Book> getTrendingBooks();

    List<Book> getBooksByAuthor(Author author);

}
