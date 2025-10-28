package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.exceptions.BadArgumentsException;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryBookRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements mk.ukim.finki.wp.lab.service.BookService {
    private final InMemoryBookRepo bookRepo;

    BookService(InMemoryBookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Book> listAll() {
        return bookRepo.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        if (text == null || text.isEmpty() || rating == null || rating.isNaN()) {
            throw new BadArgumentsException("Bad arguments for searching a book");
        }
        else{
            return bookRepo.searchBooks(text, rating);
        }
    }
}
