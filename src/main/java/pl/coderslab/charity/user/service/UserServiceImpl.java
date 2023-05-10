package pl.coderslab.charity.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.role.entity.Role;
import pl.coderslab.charity.role.repository.RoleRepository;
import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }



    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByUserId(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public User updateUser(User user) {
        if(user.getPassword()==null){
            Optional<User> userToEdit = userRepository.findById(user.getId());
            userToEdit.ifPresent(value -> user.setPassword(value.getPassword()));
        }else{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if(user.getEnabled() == null){
            user.setEnabled(1);
        }
        return userRepository.save(user);
    }

    @Override
    public HttpStatus remove(Long id) {
                userRepository.deleteById(id);
        return HttpStatus.OK;
    }
}