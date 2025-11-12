package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.exceptions.BadArgumentsException;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements mk.ukim.finki.wp.lab.service.AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author addAuth(String name, String surname, String country, String bio) {
        if(name == null || surname == null || country == null || bio == null
                || name.isEmpty() || surname.isEmpty() || country.isEmpty() || bio.isEmpty()){
            throw new BadArgumentsException("You can't add like that.");
        }
        return authorRepository.save(new Author(name, surname, country, bio));
    }

    @Override
    public void deleteAuth(Long id) {
        authorRepository.delete(id);
    }

    @Override
    public Author editAuth(Long id, String name, String surname, String country, String bio) {
        Author a = authorRepository.findById(id);
        a.setName(name);
        a.setSurname(surname);
        a.setCountry(country);
        a.setBiography(bio);
        return authorRepository.save(a);
    }
}