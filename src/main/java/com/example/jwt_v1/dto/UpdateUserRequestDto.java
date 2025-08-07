package com.example.jwt_v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDto {

    private String name;  // Tên người dùng mới
    private String email;
    private String password;  // Mật khẩu mới


}
