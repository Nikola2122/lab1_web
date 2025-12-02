package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.model.exceptions.BadArgumentsException;
import mk.ukim.finki.wp.lab.service.impl.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

@Controller
@RequestMapping("/controller/bookReservation")
public class BookReservationController {
    private final BookReservationService bookReservationService;

    public BookReservationController(BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
    }

    @PostMapping("/place")
    String reserve(@RequestParam(required = false) Long bookId,
                   @RequestParam(required = false) String readerName,
                   @RequestParam(required = false) String readerAddress,
                   @RequestParam(required = false) String numCopies, Model model, HttpServletRequest request){

        int numberOfCopies = !numCopies.isEmpty() ? Integer.parseInt(numCopies) : 0;
        String ip = request.getRemoteAddr();

        try{
            this.bookReservationService.placeReservation(bookId, readerName, readerAddress, numberOfCopies);
            model.addAttribute("bookTitle", bookId);
            model.addAttribute("readerName", readerName);
            model.addAttribute("readerAddress", readerAddress);
            model.addAttribute("numberOfCopies", numberOfCopies);
            model.addAttribute("ip", ip);
            return "reservationConfirmation";
        }
        catch (BadArgumentsException ex){
            model.addAttribute("hasError", true);
            model.addAttribute("error", ex.getMessage());
            return "reservationConfirmation";
        }
    }
}

