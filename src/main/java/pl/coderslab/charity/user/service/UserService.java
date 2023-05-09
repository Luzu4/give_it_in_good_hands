package pl.coderslab.charity.user.service;

import org.springframework.http.HttpStatus;
import pl.coderslab.charity.user.entity.User;

import java.util.List;

public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);

    List<User> findAllUsers();
    User findByUserId(Long id);

    User updateUser(User user);

    HttpStatus remove(Long id);
}