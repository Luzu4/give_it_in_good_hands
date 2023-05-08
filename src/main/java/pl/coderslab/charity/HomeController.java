package pl.coderslab.charity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.donation.service.DonationService;
import pl.coderslab.charity.institution.service.InstitutionService;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;

    @RequestMapping("/")
    public String homeAction(Model model) {
        model.addAttribute("institutionsList", institutionService.findAllInstitutions());
        model.addAttribute("quantityDonations", donationService.getSumOfQuantity());
        model.addAttribute("sumOfGifts", donationService.getCountOfGifts());
        return "index";
    }

    @GetMapping("/about")
    @ResponseBody
    public String about() {
        return "Here you can find some details for logged users";
    }

    @GetMapping("/403")
    @ResponseBody
    public String error() {
        return "403";
    }


}
