package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.BookReservation;

public interface BookReservationService {
    BookReservation placeReservation(Long bookId, String readerName, String readerAddress, int numberOfCopies);
}
