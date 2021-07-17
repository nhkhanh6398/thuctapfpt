package vn.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.fpt.exception.NotAvailableException;
import vn.fpt.exception.QuantityZeroException;
import vn.fpt.exception.WrongCodeException;
import vn.fpt.model.AccountMember;
import vn.fpt.model.Book;
import vn.fpt.service.AuthorService;
import vn.fpt.service.BookService;
import vn.fpt.service.CatagoryService;
import vn.fpt.service.CodeBookService;

@Controller
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    AuthorService authorService;
    @Autowired
    CatagoryService catagoryService;
    @Autowired
    CodeBookService codeBookService;


    @GetMapping("/books")
    public String homeBook(@PageableDefault(value = 5) Pageable pageable, Model model,
                           @SessionAttribute(value = "accountMember", required = false) AccountMember accountMember,
                           @CookieValue(value = "loginCookie", defaultValue = " ") String cookieUser) {

        if ("admin@gmail.com".equals(cookieUser)) {
            accountMember = new AccountMember();
            accountMember.setAccount(cookieUser);
        }
        if (accountMember == null || "guest".equals(accountMember.getAccount())){
            return "redirect:/login";
        }
        model.addAttribute("book", bookService.findAll(pageable));
        return "/book/homeBook";
    }

    @GetMapping("/book/{id}")
    public String findBookById(@PathVariable("id") int id, Model model) {
        final Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        return "/book/homeBooks";
    }

    @GetMapping("/add")
    public String homeAddBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("catagory", catagoryService.findAllCatagory());
        model.addAttribute("author", authorService.findAllAuthors());
        return "/book/addBook";
    }

    @PostMapping("/addBook")
    public String addBook(@Validated @ModelAttribute Book book, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("catagory", catagoryService.findAllCatagory());
            model.addAttribute("author", authorService.findAllAuthors());
            return "/book/addBook";
        }
        bookService.save(book);
        redirectAttributes.addFlashAttribute("message", "Book " + book.getNameBook() + " created");
        return "redirect:/books";
    }

    @GetMapping("/update/{id}")
    public String homeEdit(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        Book book = bookService.findBookById(id);

        if (book != null) {
            model.addAttribute("catagory", catagoryService.findAllCatagory());
            model.addAttribute("author", authorService.findAllAuthors());

            model.addAttribute("book", book);
            return "/book/editBook";
        } else {
            redirectAttributes.addFlashAttribute("message", "Not found ");
            return "redirect:/books";
        }
    }

    @PostMapping("/edit")
    public String edit(@Validated @ModelAttribute Book book, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("catagory", catagoryService.findAllCatagory());
            model.addAttribute("author", authorService.findAllAuthors());

            return "/book/editBook";
        }
        bookService.save(book);
        redirectAttributes.addFlashAttribute("message", "Book " + book.getNameBook() + " edit");
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        bookService.remove(id);
//        model.addAttribute("book", bookService.findAll());
        return "redirect:/books";
    }

    @RequestMapping("/searchBook")
    public String searchBook(@RequestParam("keyword") String keyword, Model model, @PageableDefault(3) Pageable pageable) {
        Page<Book> page = bookService.findAllByNameBookContaining(keyword, pageable);

        model.addAttribute("book", page);
        model.addAttribute("keyword", keyword);
        return "/book/homeBook";
    }

    @GetMapping("/borrow")
    public String brrow(Model model, @RequestParam int id,@SessionAttribute(value = "accountMember", required = false) AccountMember accountMember,
                        @CookieValue(value = "loginCookie", defaultValue = " ") String cookieUser) throws NotAvailableException {
        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("availableCode", bookService.getNextAvailableCode(book));
        return "/view/bookView";
    }

    @PostMapping("/borrowBook")
    public String borrowBook(@RequestParam int id,@ModelAttribute Book book, @RequestParam int usedCode, RedirectAttributes redirectAttributes) throws QuantityZeroException {
        Book books = bookService.findBookById(id);
        if (books.getQuantity()<=0){
            throw new QuantityZeroException();
       }
        bookService.borrow(book, usedCode);
        redirectAttributes.addFlashAttribute("message", usedCode + "borrow");
        return "redirect:/bookview/" + book.getId();
    }

    @GetMapping("/viewborrow")
    public String viewBorrow(Model model) {
        model.addAttribute("listBorrow", codeBookService.findAll());
        return "/borrow/viewBorrow";
    }

    @ExceptionHandler(NotAvailableException.class)
    public String notAvailable() {
        return "/exception/error_not_available";
    }

    @ExceptionHandler(WrongCodeException.class)
    public String wrongCode() {
        return "/exception/error_wrong_code";
    }
    @ExceptionHandler(QuantityZeroException.class)
    public String quantityZero(){
        return "/exception/error_not_available";
    }
}
