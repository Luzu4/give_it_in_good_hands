package pl.coderslab.charity.donation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.donation.entity.Donation;
import pl.coderslab.charity.donation.repository.DonationRepository;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;


    public Long getSumOfQuantity() {
        return donationRepository.getSumOfQuantity().orElse(0L);
    }

    public Long getCountOfGifts() {
        return donationRepository.count();
    }

    public Donation saveNewDonation(Donation donation) {
        return donationRepository.save(donation);
    }
}
