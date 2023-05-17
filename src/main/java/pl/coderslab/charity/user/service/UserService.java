package pl.coderslab.charity.user.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import pl.coderslab.charity.role.entity.Role;
import pl.coderslab.charity.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);

    List<User> findAllUsers(Optional<Boolean> isAdmin);

    User findByUserId(Long id);

    User updateUser(User user);

    HttpStatus remove(Long id);


    User editUserData(User user, Authentication authentication);
}