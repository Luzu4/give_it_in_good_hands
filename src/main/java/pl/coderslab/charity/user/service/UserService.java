package pl.coderslab.charity.user.service;

import pl.coderslab.charity.user.entity.User;

public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);
}