package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
    String title;
    String genre;
    Double averageRating;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    Author author;

    public Book(String title, String genre, double averageRating, Author author) {
        this.title = title;
        this.genre = genre;
        this.averageRating = averageRating;
        this.author = author;
    }
}
