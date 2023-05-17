package pl.coderslab.charity.donation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.donation.entity.Donation;
import pl.coderslab.charity.user.entity.User;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("select sum(quantity) from Donation")
    Long getSumOfQuantity();

    List<Donation> findAllByUserOrderByTakenAscTakenDateAscCreatedAtAsc(User user);

}
