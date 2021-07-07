package vn.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.fpt.model.Author;
import vn.fpt.service.AuthorService;

@Controller
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping("/authors")
    public String home(Model model){
        model.addAttribute("authors",authorService.findAllAuthors());
        return "/author/homeAuthor";
    }
    @GetMapping("/addAuthor")
    public String homeAddAuthor(Author author, Model model){
        model.addAttribute("author", new Author());
        return "/author/addAuthor";
    }
    @PostMapping("/addAuthor")
    public String addAuthor(@Validated @ModelAttribute Author author, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasFieldErrors()){
            return "/author/addAuthor";
        }
        authorService.createAuthor(author);
        redirectAttributes.addFlashAttribute("message","Author "+ author.getName()+ " created");
        return "redirect:/authors";
    }
    @GetMapping("/updateAuthor/{id}")
    public String homeEditAuthor(@PathVariable int id, Model model){
        model.addAttribute("authors", authorService.findAuthorById(id));
        return "/author/editAuthor";
    }
    @PostMapping("/updateAuthor")
    public String editAuthor(@ModelAttribute Author author, RedirectAttributes redirectAttributes){
        authorService.updateAuthor(author);
        redirectAttributes.addFlashAttribute("message","Author "+ author.getName()+ " edited");
        return "redirect:/authors";
    }
    @GetMapping("/deleteAuthor/{id}")
    public String deleteAuthor(@PathVariable int id){
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }
}
