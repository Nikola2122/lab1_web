package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

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
}
