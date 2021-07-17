package vn.fpt.controller;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.fpt.exception.NotAvailableException;
import vn.fpt.exception.WrongCodeException;
import vn.fpt.model.Book;
import vn.fpt.model.CodeBook;
import vn.fpt.service.BookService;
import vn.fpt.service.CatagoryService;
import vn.fpt.service.CodeBookService;
import vn.fpt.validation.ReturnCodeWrapper;

import java.util.List;

@Controller
public class ViewController {
    @Autowired
    BookService bookService;
    @Autowired
    CatagoryService catagoryService;
    @Autowired
    CodeBookService codeBookService;


    @GetMapping("/")
    public String homeView(Model model, @PageableDefault (value = 6) Pageable pageable ) {
        model.addAttribute("book", bookService.findAll(pageable));
        model.addAttribute("catagory", catagoryService.findAllCatagory());
        return "/view/view";
    }

    @GetMapping("/catagorybook/{id}")
    public String findByNameCatagory(@PathVariable String id, Model model) {
        model.addAttribute("book", bookService.findBookByNameCatagory(id));
        model.addAttribute("catagory", catagoryService.findAllCatagory());
        return "/view/view";
    }

    @PostMapping("/findBookByName")
    public String findBook(@RequestParam("key") String key, Model model) {
        model.addAttribute("book", bookService.findAllByName(key));
        model.addAttribute("catagory", catagoryService.findAllCatagory());
        return "/view/view";
    }

    @PostMapping("/search")
    public String search(@RequestParam("key") String key, @RequestParam("change") String change, Model model) {
        if (change.equals("all")){
            model.addAttribute("book",bookService.findAllByBookOrCatagoryOrAuthor(key));
            List<Book> books = bookService.findAllByBookOrCatagoryOrAuthor(key);

            model.addAttribute("catagory", catagoryService.findAllCatagory());
            return "/view/view";
        }
        if (change.equals("book")) {
            model.addAttribute("book", bookService.findAllByName(key));
            model.addAttribute("catagory", catagoryService.findAllCatagory());
            return "/view/view";
        } else if (change.equals("author")){
            model.addAttribute("book", bookService.findByAuthorContaining(key));
            model.addAttribute("catagory", catagoryService.findAllCatagory());
            return "/view/view";
        }
        return null;
    }
    @GetMapping("/bookview/{id}")
    public String bookView(@PathVariable int id, Model model,@ModelAttribute ReturnCodeWrapper returnCodeWrapper) throws NotAvailableException {
        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("catagory", catagoryService.findAllCatagory());
        model.addAttribute("availableCode", bookService.getNextAvailableCode(book));
        model.addAttribute("returnCodeWrapper", returnCodeWrapper);
        return "/view/bookView";
    }
    @GetMapping("/return")
        public String returnView(@PathVariable int id, Model model, @ModelAttribute ReturnCodeWrapper returnCodeWrapper){
        Book book = bookService.findBookById(id);
        model.addAttribute("returnCodeWrapper", returnCodeWrapper);
        model.addAttribute("book", book);
        return "/view/bookView";
    }
    @PostMapping("/returnBook")
    public String returnBook(@ModelAttribute Book book, @RequestParam  Integer returnCodeWrapper
            ,Model model) throws WrongCodeException, NotAvailableException {
        if (returnCodeWrapper < 10000 || returnCodeWrapper>99999){
//            model.addAttribute("returnCodeWrapper", returnCodeWrapper);
//            model.addAttribute("book", book);
            return "redirect:/bookview/" + book.getId();
        }
//        if (result.hasFieldErrors()) {
//            if (result.hasFieldErrors()) {
//                model.addAttribute("returnCodeWrapper", returnCodeWrapper);
//                model.addAttribute("book", book);
//                return "/view/bookView";
//            }
//        }
        bookService.returnBook(book,returnCodeWrapper);
        model.addAttribute("book", book);
        return "redirect:/bookview/"+book.getId();
    }
    @ExceptionHandler(NotAvailableException.class)
    public String notAvailable() {
        return "/exception/error_not_available";
    }

    @ExceptionHandler(WrongCodeException.class)
    public String wrongCode() {
        return "/exception/error_wrong_code";
    }

}
