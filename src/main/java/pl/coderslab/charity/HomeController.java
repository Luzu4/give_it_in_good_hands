package pl.coderslab.charity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.institution.service.InstitutionService;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final InstitutionService institutionService;

    @RequestMapping("/")
    public String homeAction(Model model){
        return "index";
    }
}
