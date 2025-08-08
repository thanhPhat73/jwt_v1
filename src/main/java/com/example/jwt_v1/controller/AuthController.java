package com.example.jwt_v1.controller;

import com.example.jwt_v1.dto.LoginDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("api/login")
    public ResponseEntity<LoginDto> Login(@RequestBody @Valid LoginDto loginDto) throws Exception {
        //Nạp input gồm username/password vào Security
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

       // mặc định cơ chế thằng spring security nó sẽ lưu người dùng vào memory
        // nó không hề biết mình lưu trữ ở DB
        // => bây giờ phải nói cho java spring biết tao lưu trữ ở đau và mày làm cho tao

        //func đặc biệt phụ trách xác thực người dùng => cần viết hàm loadUserByUsername
        // ghi đè lại nó
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return ResponseEntity.status(HttpStatus.OK).body(loginDto);
    }
}
