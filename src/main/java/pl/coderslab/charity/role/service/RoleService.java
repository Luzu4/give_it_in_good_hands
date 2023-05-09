package pl.coderslab.charity.role.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.role.entity.Role;
import pl.coderslab.charity.role.repository.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> findAll() {
       return roleRepository.findAll();
    }
}
