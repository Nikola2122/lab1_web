package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.exceptions.BadArgumentsException;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error,
                               @RequestParam(required = false) Long authorId,
                               Model model){
        if(authorId != null){
            model.addAttribute("books", bookService.searchBooks(authorId));
        }else {
            model.addAttribute("books", bookService.listAll());
        }
        model.addAttribute("authors", authorService.findAll());
        if(error!=null && !error.isEmpty()){
            model.addAttribute("error", error);
            model.addAttribute("hasError", true);
        }
        return "listBooks";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId){
        bookService.saveBook(title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @PostMapping("/edit/{id}")
    public String saveBook(@PathVariable Long id,
                           @RequestParam(required = false) String title,
                           @RequestParam(required = false) String genre,
                           @RequestParam(required = false) Double averageRating,
                           @RequestParam(required = false) Long authorId){
        try {
            bookService.editBook(id, title, genre, averageRating, authorId);
        }catch (BadArgumentsException ex){
            return "redirect:/books?error=" + ex.getMessage();
        }
        return "redirect:/books";
    }


    @GetMapping("/add-form")
    public String getAddForm(Model model){
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return "redirect:/books";
    }
    @GetMapping("/edit-form/{id}")
    public String editBook(@PathVariable Long id, Model model){
        model.addAttribute("authors", authorService.findAll());
        Book b = bookService.getBook(id);
        if(b==null){
            return "redirect:/books?error=Book not found";
        }
        model.addAttribute("book", bookService.getBook(id));
        return "book-form";
    }
}
