package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books;
    public static List<BookReservation> reservations;

    @PostConstruct
    void init(){
        books = new ArrayList<>();
        reservations = new ArrayList<>();
        books.add(new Book("Harry Potter", "Fantasy", 10));
        books.add(new Book("The Hobbit", "Fantasy", 9));
        books.add(new Book("Pride and Prejudice", "Romance", 8));
        books.add(new Book("Romeo and Juliet", "Romance", 7));
        books.add(new Book("The Little Prince", "Children", 6));
        books.add(new Book("Charlie and the Chocolate Factory", "Children", 5));
        books.add(new Book("The Chronicles of Narnia", "Fantasy", 4));
        books.add(new Book("Emma", "Romance", 3));
        books.add(new Book("Peter Pan", "Children", 2));
        books.add(new Book("Percy Jackson", "Fantasy", 1));
    }
}
