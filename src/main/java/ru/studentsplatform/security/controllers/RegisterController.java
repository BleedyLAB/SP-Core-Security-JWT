package ru.studentsplatform.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.security.model.User;
import ru.studentsplatform.security.service.UserService;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

	private final UserService userService;

	@Autowired
	public RegisterController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<?> register(@RequestBody User user) {
		if (userService.saveUser(user)) {
			return new ResponseEntity<>("Register sucsess", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
	}
}
