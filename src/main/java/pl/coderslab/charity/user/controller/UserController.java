package pl.coderslab.charity.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.service.UserServiceImpl;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @ModelAttribute("id")
    public Long userId(@AuthenticationPrincipal UserDetails user){
        if(user != null){
            return userService.findByUserName(user.getUsername()).getId();
        }
            return null;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String createUser(User user) {
        userService.saveUser(user);
        return "login";
    }

    @GetMapping("/users/edit/{id}")
    public String updateUserForm(@AuthenticationPrincipal UserDetails user, @PathVariable Long id, Model model) {
        if(Objects.equals(userService.findByUserName(user.getUsername()).getId(), id)){
            System.out.println("userService.findByUserId(id) = " + userService.findByUserId(id));
            model.addAttribute("user", userService.findByUserId(id));
            return "/editUserForm";
        }else{
            return "redirect:/";
        }
    }

    @PostMapping("/users/edit/{id}")
    public String updateUser(@PathVariable Long id, User user, @AuthenticationPrincipal UserDetails loggedUser) {
        if(Objects.equals(userService.findByUserName(loggedUser.getUsername()).getId(), id)){
            userService.updateUser(user);
        }
        return "redirect:/";
    }
}