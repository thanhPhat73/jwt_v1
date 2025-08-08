package com.example.jwt_v1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    @NotBlank(message = "username không được để trống!")
    private String username;
    @NotBlank(message = "password không được để trống!")
    private String password;
}
