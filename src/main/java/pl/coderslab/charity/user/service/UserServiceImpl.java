package pl.coderslab.charity.user.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

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
    public List<User> findAllUsers(Optional<Boolean> isAdmin) {
        if(isAdmin.isPresent()){
            Role role = roleRepository.findByName("ROLE_ADMIN");
            return isAdmin.get() ? userRepository.findAllByRolesContains(role) : userRepository.findAll() ;
        }
        return userRepository.findAll();
    }


    @Override
    public User findByUserId(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public HttpStatus remove(Long id) {
                userRepository.deleteById(id);
        return HttpStatus.OK;
    }

    @Override
    public User editUserData(User user, Authentication authentication){
        User userToEdit = userRepository.findByUsername(authentication.getName());
        userToEdit.setFirstname(user.getFirstname());
        userToEdit.setSurname(user.getSurname());
        userToEdit.setUsername(user.getUsername());
        if(user.getPassword()!=null && !user.getPassword().equals(userToEdit.getPassword())){
            userToEdit.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(userToEdit);

    }
}