package ru.studentsplatform.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.studentsplatform.security.jwtsecurity.SecurityUser;
import ru.studentsplatform.security.model.Role;
import ru.studentsplatform.security.model.User;
import ru.studentsplatform.security.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class UserDetailServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email).orElseThrow(
				() -> new UsernameNotFoundException("User doesn't exists"));
		return SecurityUser.fromUser(user);
	}

	@Override
	public User findUserById(Long userId) {
		Optional<User> userFromDb = userRepository.findById(userId);
		return userFromDb.orElse(new User());
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public boolean saveUser(User user) {
		/*Optional<User> userFromDb = userRepository.findByEmail(user.getEmail());

		if (userFromDb.isPresent()) {
			return false;
		}*/
		user.setRole(Role.USER);
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.setStatus(true);
		user.setLocalDateTime(LocalDateTime.now());
		System.out.println(user);
		userRepository.save(user);
		return true;
	}
}
