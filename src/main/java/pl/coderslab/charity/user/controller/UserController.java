package pl.coderslab.charity.user.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.user.service.UserService;
import pl.coderslab.charity.user.entity.User;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
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
}