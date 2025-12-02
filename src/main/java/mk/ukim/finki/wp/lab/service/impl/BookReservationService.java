package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.model.exceptions.BadArgumentsException;
import mk.ukim.finki.wp.lab.repository.BookReservationRepository;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryBookReservationRepo;
import mk.ukim.finki.wp.lab.repository.jpa.BookReservRepoJpa;
import org.springframework.stereotype.Service;


@Service
public class BookReservationService implements mk.ukim.finki.wp.lab.service.BookReservationService {
    private final BookReservRepoJpa bookReservationRepo;
    private final BookService bookService;

    BookReservationService(BookReservRepoJpa bookReservationRepository, BookService bookService) {
        this.bookReservationRepo = bookReservationRepository;
        this.bookService = bookService;
    }

    @Override
    public BookReservation placeReservation(Long bookId, String readerName, String readerAddress, int numberOfCopies) {
        if(bookId == null || readerName == null
                || readerName.isEmpty() || readerAddress == null || readerAddress.isEmpty()) {
            throw new BadArgumentsException("Bad arguments for reserving a book");
        }
        else{
            Book b = bookService.getBook(bookId);
            BookReservation bookReservation = new BookReservation(b, readerName, readerAddress, numberOfCopies);
            return this.bookReservationRepo.save(bookReservation);
        }
    }
}
