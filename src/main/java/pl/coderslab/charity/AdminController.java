package pl.coderslab.charity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.institution.entity.Institution;
import pl.coderslab.charity.institution.service.InstitutionService;
import pl.coderslab.charity.role.repository.RoleRepository;
import pl.coderslab.charity.role.service.RoleService;
import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.service.UserServiceImpl;

@Secured("ROLE_ADMIN")
@RequestMapping("/admin")
@Controller
@RequiredArgsConstructor
public class AdminController {

    private final InstitutionService institutionService;
    private final UserServiceImpl userService;
    private final RoleService roleService;

    @GetMapping
    public String adminHomePage() {
        return "/admin/index";
    }

    @GetMapping("/institutions")
    public String institutionsTable(Model model) {
        model.addAttribute("institutionList", institutionService.findAllInstitutions());
        return "/admin/institutionsTable";
    }

    @GetMapping("/institutions/create")
    public String createInstitutionForm(Model model) {
        model.addAttribute("institution", new Institution());
        return "/admin/createInstitutionForm";
    }

    @PostMapping("/institutions/create")
    public String createInstitution(Institution institution) {
        institutionService.saveInstitution(institution);
        return "/admin/createInstitutionForm";
    }

    @RequestMapping("/institutions/remove/{id}")
    public String removeInstitution(@PathVariable Long id) {
        institutionService.remove(id);
        return "redirect:/admin/institutions";
    }

    @GetMapping("/institutions/edit/{id}")
    public String updateInstitutionForm(@PathVariable Long id, Model model) {
        model.addAttribute("institution", institutionService.getById(id));
        return "/admin/createInstitutionForm";
    }

    @PostMapping("/institutions/edit/{id}")
    public String updateInstitution(Institution institution) {
        institutionService.saveInstitution(institution);
        return "redirect:/admin/institutions";
    }

    @GetMapping("/users")
    public String usersTable(Model model) {
        model.addAttribute("userList", userService.findAllUsers());
        return "/admin/usersTable";
    }

    @GetMapping("/users/edit/{id}")
    public String updateUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findByUserId(id));
        model.addAttribute("roles", roleService.findAll());
        return "/admin/editUserForm";
    }

    @PostMapping("/users/edit/{id}")
    public String updateUser(@PathVariable Long id, User user) {
        System.out.println("user = " + user);
        System.out.println("____________________________________");
        user.setPassword(userService.findByUserId(id).getPassword());
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @RequestMapping("/users/remove/{id}")
    public String removeUser(@PathVariable Long id) {
        userService.remove(id);
        return "redirect:/admin/users";
    }
}