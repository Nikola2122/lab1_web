package mk.ukim.finki.wp.lab.web.controller;


import jakarta.websocket.server.PathParam;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.exceptions.BadArgumentsException;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    String getAuth(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("authors", authorService.findAll());
        if (error != null && !error.isEmpty()) {
            model.addAttribute("error", error);
            model.addAttribute("hasError", true);
        }
        return "listAuthors";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    String add(@RequestParam(required = false) String name,
               @RequestParam(required = false) String surname,
               @RequestParam(required = false) String country,
               @RequestParam(required = false) String bio) {
        try {
            this.authorService.addAuth(name, surname, country, bio);
        }catch (BadArgumentsException ex){
            return "redirect:/authors?error=" + ex.getMessage();
        }
        return "redirect:/authors";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit/{id}")
    public String saveBook(@PathVariable Long id,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String surname,
                           @RequestParam(required = false) String country,
                           @RequestParam(required = false) String bio) {
        authorService.editAuth(id, name, surname, country, bio);
        return "redirect:/authors";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        authorService.deleteAuth(id);
        return "redirect:/authors";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit-form/{id}")
    public String editAuthor(@PathVariable Long id, Model model){
        Author a = authorService.findById(id);
        if(a==null){
            return "redirect:/authors?error=Author not found";
        }
        model.addAttribute("author", a);
        return "auth-form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-form")
    public String addAuthor(){
        return "auth-form";
    }
}