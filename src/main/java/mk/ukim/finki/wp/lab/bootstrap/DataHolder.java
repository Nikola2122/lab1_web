package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books;
    public static List<BookReservation> reservations;
    public static List<Author> authors;

    @PostConstruct
    void init(){
        books = new ArrayList<>();
        reservations = new ArrayList<>();
        authors = new ArrayList<>();
        Author author = new Author("Nikola","Iliev","Mkd","Something...");
        Author author2 = new Author("Ivo","Banco","Serb","Something 2...");
        Author author3 = new Author("Meli","Meli","Ang","Something 3...");
        authors.add(author);
        authors.add(author2);
        authors.add(author3);

        books.add(new Book("Harry Potter", "Fantasy", 10, author));
        books.add(new Book("The Hobbit", "Fantasy", 9, author));
        books.add(new Book("Pride and Prejudice", "Romance", 8, author));
        books.add(new Book("Romeo and Juliet", "Romance", 7, author2));
        books.add(new Book("The Little Prince", "Children", 6, author3));
        books.add(new Book("Charlie and the Chocolate Factory", "Children", 5, author2));
        books.add(new Book("The Chronicles of Narnia", "Fantasy", 4, author3));
        books.add(new Book("Emma", "Romance", 3, author));
        books.add(new Book("Peter Pan", "Children", 2, author2));
        books.add(new Book("Percy Jackson", "Fantasy", 1, author3));
    }
}
