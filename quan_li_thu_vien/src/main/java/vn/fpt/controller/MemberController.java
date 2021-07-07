package vn.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.fpt.model.AccountMember;
import vn.fpt.model.Member;
import vn.fpt.service.MemberService;

import java.sql.Date;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/createAccount")
    public String homeCreate(Member member, Model model) {
        model.addAttribute("member", new Member());
        return "member/createMember";
    }

    @PostMapping("/addMember")
    public String create(@Validated @ModelAttribute Member member, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        new Member().validate(member,bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "member/createMember";
        } else {
            member.getAccount().setDateCreate(new Date(System.currentTimeMillis()));
            redirectAttributes.addFlashAttribute("message", "Member " + member.getNameMember()
                    + " created!" + "\n" + "Please Login " + member.getAccount().getAccount());
            memberService.createAccount(member);
            return "redirect:/";
        }
    }
    @GetMapping("/member")
    public String listMember(Model model){
        model.addAttribute("member", memberService.findAllAccount());
        return "member/homeMember";
    }

}
