package pl.coderslab.charity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.donation.service.DonationService;
import pl.coderslab.charity.institution.service.InstitutionService;
import pl.coderslab.charity.user.service.UserService;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;

    @ModelAttribute("id")
    public Long userId(@AuthenticationPrincipal UserDetails user){
        System.out.println("Siemanko1");
        if(user != null){
            System.out.println("SIemanko");
            System.out.println("user = " + user);
            return userService.findByUserName(user.getUsername()).getId();
        }
        return null;
    }

    @RequestMapping("/")
    public String homeAction(Model model) {
        model.addAttribute("institutionsList", institutionService.find4Institutions());
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
