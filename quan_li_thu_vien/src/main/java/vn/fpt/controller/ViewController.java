package vn.fpt.controller;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.fpt.service.BookService;
import vn.fpt.service.CatagoryService;

@Controller
public class ViewController {
    @Autowired
    BookService bookService;
    @Autowired
    CatagoryService catagoryService;
    @GetMapping("/")
    public String homeView(Model model){
        model.addAttribute("book",bookService.findAll());
        model.addAttribute("catagory",catagoryService.findAllCatagory());
        return "/view/view";
    }
    @GetMapping("/{id}")
    public String findByNameCatagory(@PathVariable String id, Model model){
        model.addAttribute("book", bookService.findBookByNameCatagory(id));
        model.addAttribute("catagory",catagoryService.findAllCatagory());
        return "/view/view";
    }

    @PostMapping("/findBookByName")
    public String findBook(@RequestParam("key") String key, Model model){
        model.addAttribute("book",bookService.findAllByName(key));
        model.addAttribute("catagory",catagoryService.findAllCatagory());
        return "/view/view";
    }
}
