package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;

import java.util.List;

public interface AuthorService {
    public List<Author> findAll();
    public Author findById(Long id);
}
