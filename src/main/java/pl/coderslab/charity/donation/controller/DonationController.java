package pl.coderslab.charity.donation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.category.service.CategoryService;
import pl.coderslab.charity.donation.entity.Donation;
import pl.coderslab.charity.donation.service.DonationService;
import pl.coderslab.charity.institution.service.InstitutionService;

@Controller
@RequestMapping("/donations")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;
    private final CategoryService categoryService;
    private final InstitutionService institutionService;

    @GetMapping("/donate")
    public String donateForm(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("donation", new Donation());
        model.addAttribute("institutions", institutionService.findAllInstitutions());
        return "form";
    }

    @PostMapping("/donate")
    public String createDonation(Donation donation) {
        System.out.println("donation = " + donation);
        donationService.saveNewDonation(donation);
        return "form-confirmation";
    }


}
