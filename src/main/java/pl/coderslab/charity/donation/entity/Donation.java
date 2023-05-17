package pl.coderslab.charity.donation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.category.entity.Category;
import pl.coderslab.charity.institution.entity.Institution;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Positive
    private Integer quantity;

    @ManyToMany
    @NotEmpty
    private Set<Category> categories;
    @ManyToOne
    @NotNull
    private Institution institution;

    @NotBlank
    private String street;
    @NotBlank
    private String city;
    @NotBlank
    private String zipCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Future
    private LocalDate pickUpDate;
    @NotNull
    private LocalTime pickUpTime;
    private String pickUpComment;
    @Pattern(regexp="(^$|[0-9]{9})")
    @NotBlank
    private String phoneNumber;
}
