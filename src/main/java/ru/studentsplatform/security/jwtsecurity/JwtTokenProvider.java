package ru.studentsplatform.security.jwtsecurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.studentsplatform.security.service.UserService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

	private final UserService userService;


    /**
     * Данные полей храняться в application.properties
     */
	@Value("${jwt.header}")
	private String header;
	@Value("${jwt.secret}")
	private String secretKey;
	@Value("${jwt.expiration}")
	private long validityInMilliSeconds;

	public JwtTokenProvider(UserService userService) {
		this.userService = userService;
	}

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}


	public String createTokens(String userName, String role) {
		Claims claims = Jwts.claims().setSubject(userName); // Мапа для дополнительных данных, необходимых для генерации токена 
		claims.put("role", role);
		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliSeconds);
		return Jwts.builder().setClaims(claims) // Передаем в билдер класс созданый выше
				.setIssuedAt(now) // Дата создания токена
				.setExpiration(validity) // Срок годности токена 
				.signWith(SignatureAlgorithm.HS256, secretKey) // Настройка шифрования 
				.compact();
	}

	public boolean validateToken(String token) {
		try {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return !claimsJws.getBody().getExpiration().before(new Date());
		} catch (JwtException | IllegalArgumentException e) {
			throw new JwtAuthException("Jwt token is expired or invalid", HttpStatus.UNAUTHORIZED);
		}
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = this.userService.loadUserByUsername(getUserName(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUserName(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest request) {
		return request.getHeader(header);
	}

}
