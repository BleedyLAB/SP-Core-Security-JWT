package ru.studentsplatform.security.security;

import lombok.Data;

@Data
public class AuthenticationDTO {
    private String email;
    private String password;
}
