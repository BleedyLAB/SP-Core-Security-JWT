package ru.studentsplatform.security.jwtsecurity;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

/**
 * Создаем обработчик ошибок в случае неверного токена
 */
public class JwtAuthException extends AuthenticationException {
    private HttpStatus httpStatus;

    public JwtAuthException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public JwtAuthException(String msg) {
        super(msg);
    }
}
