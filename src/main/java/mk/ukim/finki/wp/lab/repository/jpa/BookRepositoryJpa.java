package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepositoryJpa extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthorId(Long authorId);
    List<Book> findAllByAuthorIdAndAverageRating(Long authorId, float averageRating);
}
