package vn.fpt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.fpt.model.AccountBook;
import vn.fpt.model.AccountMember;
import vn.fpt.model.Book;
import vn.fpt.service.AccountService;
import vn.fpt.service.BookService;
import vn.fpt.service.CatagoryService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller

public class LoginController {
    @Autowired
    AccountService accountService;
    @Autowired
    BookService bookService;
    @Autowired
    CatagoryService catagoryService;



    @GetMapping("/login")
    public String login() {
//        System.out.println(accountService.findAllByBooks());

            return "index";
    }


}
