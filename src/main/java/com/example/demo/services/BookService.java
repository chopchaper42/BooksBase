package com.example.demo.services;

import com.example.demo.entities.Author;
import com.example.demo.entities.Review;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.ReviewRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.entities.Book;
import com.example.demo.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository, ReviewRepository reviewRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
        this.authorRepository = authorRepository;
    }

    public void initModelWithUserBooks(Model model, Authentication authentication) {

    }

    public void addReview(Long bookId, Review review) {
        Book book = bookRepository.findBookById(bookId);
        book.getReviews().add(review);

        reviewRepository.save(review);
        bookRepository.save(book);
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.findById(reviewId).ifPresent(reviewRepository::delete);
    }

    public void addUsernameAndDateToReview(Review review, Authentication authentication) {
        review.setUser(userRepository.findByUsername(authentication.getName()));
        review.setPostDate(Date.valueOf(LocalDate.now()));
    }

    public List<Book> searchBooks(String searchValue) {
        List<Book> books = bookRepository.findBookByTitleContainingIgnoreCase(searchValue);
        return books;
    }

    public void createBookPage(Long bookId, Model model, Authentication authentication) {
        Book book = bookRepository.findBookById(bookId);

        model.addAttribute("book", book);

        if (authentication != null && authentication.isAuthenticated()) {
            User user = userRepository.findByUsername(authentication.getName());

            model.addAttribute("userBooks", user.getBooks());
            model.addAttribute("blank_review", new Review());
        }

        List<Book> authorBooks = bookService.getBooksByAuthor(book.getAuthor());
        model.addAttribute("authorBooks", authorBooks);
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
