package ru.studentsplatform.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.studentsplatform.security.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findUserById(Long userId);

    List<User> findAllUsers();

    boolean saveUser(User user);

}
