package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.exceptions.BadArgumentsException;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryBookRepo;
import mk.ukim.finki.wp.lab.repository.jpa.BookRepositoryJpa;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements mk.ukim.finki.wp.lab.service.BookService {
    private final BookRepositoryJpa bookRepo;
    private final AuthorService authorService;

    BookService(BookRepositoryJpa bookRepo, AuthorService authorService) {
        this.bookRepo = bookRepo;
        this.authorService = authorService;
    }

    @Override
    public List<Book> listAll() {
        return bookRepo.findAll();
    }

    @Override
    public List<Book> searchBooks(Long id) {
        return bookRepo.findAllByAuthorId(id);
    }

    @Override
    public Book saveBook(String title, String genre, Double rating, Long authorId) {
        Author a = authorService.findById(authorId);
        Book b = new Book(title, genre, rating, a);
        bookRepo.save(b);
        return b;
    }

    @Override
    public Book editBook(Long id, String title, String genre, Double rating, Long authorId) {
        if(title == null || genre == null || rating == null || authorId == null
        || title.isEmpty() || genre.isEmpty() || rating.isNaN()) {
            throw new BadArgumentsException("Bad arguments for editing a book");
        }
        Author a = authorService.findById(authorId);
        Book b = this.bookRepo.findById(id).get();
        b.setAuthor(a);
        b.setGenre(genre);
        b.setTitle(title);
        b.setAverageRating(rating);
        bookRepo.save(b);
        return b;
    }

    @Override
    public void deleteBook(Long id) {
        Book b = bookRepo.findById(id).get();
        bookRepo.delete(b);
    }

    @Override
    public Book getBook(Long id) {
        return bookRepo.findById(id).get();
    }
}
