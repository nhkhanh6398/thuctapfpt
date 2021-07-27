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
@SessionAttributes("accountMember")
public class LoginController {
    @Autowired
    AccountService accountService;
    @Autowired
    BookService bookService;
    @Autowired
    CatagoryService catagoryService;

    @ModelAttribute("accountMember")
    public AccountMember accountMember() {
        return new AccountMember();
    }

    @GetMapping("/login")
    public String login() {
//        System.out.println(accountService.findAllByBooks());

            return "index";
    }

    @PostMapping("/loginUser")
    public String checkLogin(@RequestParam("email") String user, @RequestParam("pass") String pass, @RequestParam(value = "check", defaultValue = "") String check,
                             @PageableDefault(value = 5) Pageable pageable, @SessionAttribute("accountMember") AccountMember accountMember,
                             @CookieValue(value = "loginCookie", defaultValue = " ") String cookieUser,
                             Model model, RedirectAttributes redirectAttributes, HttpServletResponse response) {
        AccountMember account = accountService.checkLogin(user, pass);
        if (account != null) {
            accountMember.setAccount(user);
            accountMember.setPass(pass);
            accountMember.setId(account.getId());
            if (!check.equals("")) {
                Cookie cookie1 = new Cookie("loginCookie", user);
                Cookie cookie2 = new Cookie("loginPass", pass);
                cookie1.setMaxAge(24 * 60 * 60);
                cookie2.setMaxAge(24 * 60 * 60);
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            } else {
                Cookie cookie = new Cookie("loginCookie", user);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }if (accountMember.getAccount().equals("admin@gmail.com")){
                model.addAttribute("book", bookService.findAll(pageable));
                return "book/homeBook";
            }
            model.addAttribute("user", accountMember.getAccount());
            model.addAttribute("catagory", catagoryService.findAllCatagory());
            model.addAttribute("book", bookService.findAll(pageable));
            return "/view/view";
        } else {

        redirectAttributes.addFlashAttribute("message", "User or Pass wrong");
        return "redirect:/login";
    }}

    @GetMapping("/logout")
    public String logOut(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("loginCookie") || cookie.getName().equals("loginPass")){
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        session.removeAttribute("userName");
        return "redirect:/";
    }
}
