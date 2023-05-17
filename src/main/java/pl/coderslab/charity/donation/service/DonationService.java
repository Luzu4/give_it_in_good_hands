package pl.coderslab.charity.donation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.donation.entity.Donation;
import pl.coderslab.charity.donation.repository.DonationRepository;
import pl.coderslab.charity.user.repository.UserRepository;
import pl.coderslab.charity.user.service.UserServiceImpl;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;
    private final UserServiceImpl userService;


    public Long getSumOfQuantity() {
        return donationRepository.getSumOfQuantity();
    }

    public Long getCountOfGifts() {
        return donationRepository.count();
    }

    public Donation saveNewDonation(Donation donation, Authentication authentication) {
        donation.setTaken(false);
        donation.setUser(userService.findByUserName(authentication.getName()));
        return donationRepository.save(donation);
    }

    public List<Donation> findDonationsByUser(Authentication authentication){
        return donationRepository.findAllByUserOrderByTakenAscTakenDateAscCreatedAtAsc(userService.findByUserName(authentication.getName()));
    }

    public Donation findDonationById(Long id){
        return donationRepository.findById(id).orElse(null);
    }
}
