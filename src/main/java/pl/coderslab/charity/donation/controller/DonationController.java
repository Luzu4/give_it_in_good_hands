package pl.coderslab.charity.donation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.category.service.CategoryService;
import pl.coderslab.charity.donation.entity.Donation;
import pl.coderslab.charity.donation.service.DonationService;
import pl.coderslab.charity.institution.service.InstitutionService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/donations")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;
    private final CategoryService categoryService;
    private final InstitutionService institutionService;

    @Secured("ROLE_USER")
    @GetMapping("/donate")
    public String donateForm(Model model) {
        Date todayDate = new Date();

        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("donation", new Donation());
        model.addAttribute("institutions", institutionService.findAllInstitutions());
        model.addAttribute("todayDate", new SimpleDateFormat("yyyy-MM-dd").format(todayDate));
        return "form";
    }

    @PostMapping("/donate")
    public String createDonation(Donation donation) {
        donationService.saveNewDonation(donation);
        return "form-confirmation";
    }


}
