package pl.coderslab.charity.institution.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.charity.institution.entity.Institution;
import pl.coderslab.charity.institution.repository.InstitutionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    public List<Institution> findAllInstitutions() {
        return institutionRepository.findAll();
    }
    public List<Institution> find4Institutions() {
        return institutionRepository.findAll(Pageable.ofSize(4)).getContent();
    }

    public Institution getById(Long id){
        return institutionRepository.getById(id);
    }

    public Institution saveInstitution(Institution institution){
        return institutionRepository.save(institution);
    }

    public HttpStatus remove(Long id){
        institutionRepository.deleteById(id);
        return HttpStatus.OK;
    }

}
