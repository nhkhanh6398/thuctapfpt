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
import vn.fpt.model.Catagory;
import vn.fpt.service.CatagoryService;

@Controller
public class CatagoryController {
    @Autowired
    CatagoryService catagoryService;

    @GetMapping("/categories")
    public String home(Model model) {
        model.addAttribute("categories", catagoryService.findAllCatagory());
        return "/catagory/homeCatagory";
    }

    @GetMapping("/addCategory")
    public String homeAddCatagory(Catagory catagory, Model model) {
        model.addAttribute("catagory", new Catagory());
        return "/catagory/addCatagory";
    }

    @PostMapping("/addCatagory")
    public String addCatagory(@Validated @ModelAttribute Catagory catagory, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasFieldErrors()) {
            return "/catagory/addCatagory";
        }
        catagoryService.createCatagory(catagory);
        redirectAttributes.addFlashAttribute("message", "Catagory " + catagory.getNameCatagory() + " created");
        return "redirect:/categories";
    }

    @GetMapping("/updateCategory/{id}")
    public String homeEditCatagory(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        Catagory catagory = catagoryService.findCatagoryById(id);
        if (catagory != null) {
            model.addAttribute("catagory", catagory);
            return "/catagory/editCatarogy";
        } else {
            redirectAttributes.addFlashAttribute("message", "Not found ");
            return "redirect:/categories";
        }
    }

    @PostMapping("/editCatagory")
    public String editCatagory(@ModelAttribute Catagory catagory, RedirectAttributes redirectAttributes) {
        catagoryService.updateCatagory(catagory);
        redirectAttributes.addFlashAttribute("message", "Catagory " + catagory.getNameCatagory() + " edit");
        return "redirect:/categories";
    }

    @GetMapping("/removeCategory/{id}")
    public String deleteCatagory(@PathVariable("id") int id) {
        catagoryService.deleteCatagory(id);
        return "redirect:/categories";
    }
}
