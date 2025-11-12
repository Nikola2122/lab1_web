package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class InMemoryAuthorRepository implements mk.ukim.finki.wp.lab.repository.AuthorRepository {
    @Override
    public List<Author> findAll() {
        return DataHolder.authors;
    }

    @Override
    public Author findById(Long id) {
        return DataHolder.authors.stream().filter(s-> Objects.equals(s.getId(), id)).findFirst().orElse(null);
    }

    @Override
    public void delete(Long id) {
        DataHolder.authors.removeIf(a -> a.getId().equals(id));
    }

    @Override
    public Author save(Author b) {
        DataHolder.authors.removeIf(c-> c.getId().equals(b.getId()));
        DataHolder.authors.add(b);
        return b;
    }
}