package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();
    List<Book> searchBooks(Long id);
    Book saveBook(String title, String genre, Double rating, Long authorId);
    void deleteBook(Long id);
    Book getBook(Long id);
    Book editBook(Long id, String title, String genre, Double rating, Long authorId);
    public Page<Book> find(String title, Double rating, Long authorId, Integer pageNum, Integer pageSize);
}
