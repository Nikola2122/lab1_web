package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.model.exceptions.BadArgumentsException;
import mk.ukim.finki.wp.lab.repository.BookReservationRepository;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryBookReservationRepo;
import org.springframework.stereotype.Service;


@Service
public class BookReservationService implements mk.ukim.finki.wp.lab.service.BookReservationService {
    private final InMemoryBookReservationRepo bookReservationRepo;

    BookReservationService(InMemoryBookReservationRepo bookReservationRepository) {
        this.bookReservationRepo = bookReservationRepository;
    }

    @Override
    public BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies) {
        if(bookTitle == null || bookTitle.isEmpty() || readerName == null
                || readerName.isEmpty() || readerAddress == null || readerAddress.isEmpty()) {
            throw new BadArgumentsException("Bad arguments for reserving a book");
        }
        else{
            BookReservation bookReservation = new BookReservation(bookTitle, readerName, readerAddress, numberOfCopies);
            return this.bookReservationRepo.save(bookReservation);
        }
    }
}
