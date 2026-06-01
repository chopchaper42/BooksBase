package com.example.demo.services;

import com.example.demo.entities.Author;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.entities.Book;
import com.example.demo.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public void initModelWithUserBooks(Model model, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());
        model.addAttribute("userBooks", user.getBooks());
        log.info("User books: " + user.getBooks());
    }

    public void addReview(int bookId, Authentication authentication) {

    }

    public List<Book> searchBooks(String searchValue) {
        List<Book> books = bookRepository.findBookByTitleContainingIgnoreCase(searchValue);
        return books;
    }

    public Book getBook(Long bookId) {
        return bookRepository.findBookById(bookId);
    }

    public List<Book> getBooksByAuthor(Author author) {
        return bookRepository.getBooksByAuthor(author);
    }

    public void addBookToUserBooks(long bookId, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());
        user.getBooks().add(bookRepository.findBookById(bookId));
        userRepository.save(user);
    }

    public void removeBookFromUserBooks(long bookId, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());
        Book bookToRemove = bookRepository.findBookById(bookId);
        user.getBooks().remove(bookToRemove);
        userRepository.save(user);
    }

    public List<Book> getPopularBooks() {
        return bookRepository.getTrendingBooks();
    }
}
