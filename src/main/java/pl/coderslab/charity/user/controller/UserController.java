package pl.coderslab.charity.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.category.entity.Category;
import pl.coderslab.charity.category.service.CategoryService;
import pl.coderslab.charity.donation.entity.Donation;
import pl.coderslab.charity.donation.service.DonationService;
import pl.coderslab.charity.institution.entity.Institution;
import pl.coderslab.charity.institution.service.InstitutionService;
import pl.coderslab.charity.user.service.UserService;
import pl.coderslab.charity.user.entity.User;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final DonationService donationService;
    private final CategoryService categoryService;
    private final InstitutionService institutionService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String createUser(User user) {
        userService.saveUser(user);
        return "index";
    }

    @GetMapping("/user/edit")
    public String editUserData(Model model, Authentication authentication){
        User user =userService.findByUserName(authentication.getName());
        model.addAttribute("user",user);
        return "editUserDataForm";
    }

    @PostMapping("/user/edit")
    public String editUserData(User user, Authentication authentication){
        userService.editUserData(user, authentication);
        return "index";
    }

    @GetMapping("/user/donations")
    public String showDonations(Model model, Authentication authentication){
        List<Donation> donationList = donationService.findDonationsByUser(authentication);
        model.addAttribute("donationList", donationList);
        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("categoryList", categoryList);
        List<Institution> institutionList = institutionService.findAllInstitutions();
        model.addAttribute("institutionList", institutionList);
        return "/userPanel/donationsTable";
    }
}