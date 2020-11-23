package ru.studentsplatform.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.studentsplatform.security.jwtsecurity.JwtConfigurer;

@EnableWebSecurity // Позволяет конфигурировать через HttpSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Включает анотации @PreAuthorize и @PostAuthorize
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final JwtConfigurer jwtConfigurer;

	public SecurityConfig(JwtConfigurer jwtConfigurer) {
		this.jwtConfigurer = jwtConfigurer;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()// Отключаем csrf token
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Отключаем автомотическую авторизацию
				.and()
				.authorizeRequests() // Выставояем пути и ограничения для пользователей
				.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/api/auth/login").permitAll()
				.antMatchers("/api/register").permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.apply(jwtConfigurer);// Добавляем jwt token
	}

	@Bean // Создаем бины BCrypt энкодера для проверки паролей
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
