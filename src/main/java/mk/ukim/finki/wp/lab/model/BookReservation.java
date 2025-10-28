package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookReservation {
    String bookTitle;
    String readerName;
    String readerAddress;
    int numberOfCopies;
}
