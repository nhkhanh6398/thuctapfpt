package vn.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.fpt.model.AccountMember;
import vn.fpt.service.AccountService;
import vn.fpt.service.BookService;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    AccountService accountService;
    @Autowired
    BookService bookService;
    @GetMapping("/login")
    public String login() {
        return "index";
    }

    @PostMapping("/loginUser")
    public String checkLogin(@RequestParam("email") String user, @RequestParam("pass") String pass,
                             @PageableDefault(value = 5) Pageable pageable, HttpSession session,
                             @ModelAttribute AccountMember accountMember, Model model, RedirectAttributes redirectAttributes) {
        AccountMember account =  accountService.checkLogin(user,pass);
        if (account!=null){
            session.setAttribute("userName", user);
            model.addAttribute("book",bookService.findAll(pageable));
            return "/book/homeBook";
        }else {
            redirectAttributes.addFlashAttribute("message", "User or Pass wrong" );
            return "redirect:/login";
        }
    }
    @GetMapping("/logout")
    public String logOut(HttpSession session){
        session.removeAttribute("userName");
        return "redirect:/";
    }
}
