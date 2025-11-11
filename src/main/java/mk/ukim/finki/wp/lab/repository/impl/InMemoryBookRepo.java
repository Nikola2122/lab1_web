package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class InMemoryBookRepo implements BookRepository {

    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return DataHolder.books.stream().filter(b -> b.getTitle().contains(text) && b.getAverageRating()>=rating).toList();
    }

    public Book saveBook(Book book) {
        DataHolder.books.removeIf(b -> Objects.equals(b.getId(), book.getId()));
        DataHolder.books.add(book);
        return book;
    }

    @Override
    public void deleteBook(long id) {
        DataHolder.books.removeIf(d -> d.getId()==id);
    }
    public Book findById(Long id){
        return DataHolder.books.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    }
}
