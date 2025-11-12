package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public List<Author> findAll();
    public Author findById(Long id);
    Author addAuth(String name, String surname, String country, String bio);
    void deleteAuth(Long id);
    Author editAuth(Long id, String name, String surname, String country, String bio);
}