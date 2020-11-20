package ru.studentsplatform.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.security.model.User;
import ru.studentsplatform.security.repository.UserRepository;

@RestController
@RequestMapping("/register")
public class RegisterController {
@Autowired
private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody User user){
        userRepository.save(user);
        return new ResponseEntity<>("Register sucsess", HttpStatus.ACCEPTED);
    }
}
