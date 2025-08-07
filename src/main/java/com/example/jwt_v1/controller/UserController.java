package com.example.jwt_v1.controller;

import com.example.jwt_v1.domain.User;
import com.example.jwt_v1.dto.CreateUserRequestDto;
import com.example.jwt_v1.dto.UpdateUserRequestDto;
import com.example.jwt_v1.dto.UserResponseDto;
import com.example.jwt_v1.exception.IdInvalidException;
import com.example.jwt_v1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;
    public UserController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;

        this.userService = userService;
    }




    // tạo người dùng mới
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody CreateUserRequestDto requestDto) {
        User user = new User();
        user.setName(requestDto.getName());
        // mã hóa password
        String endCodePassword = passwordEncoder.encode(requestDto.getPassword());
        user.setPassword(endCodePassword);
    //    user.setPassword(requestDto.getPassword());
        user.setEmail(requestDto.getEmail());
//        return userService.createUser(user);
        // Tạo user và nhận đối tượng trả về từ service
        UserResponseDto responseDto = userService.createUser(user);
        // Trả về ResponseEntity với mã HTTP 201 Created và đối tượng UserResponseDto
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // lấy người dùng theo id
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) throws IdInvalidException {
        UserResponseDto responseDto = userService.getUserById(id);
        // Kiểm tra nếu người dùng không tồn tại (responseDto có thể null)
        if (responseDto == null) {
            throw new IdInvalidException("User not found with ID:" +id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
    // lấy tất cả người dùng
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> responseDto = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    //cập nhật người dùng
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequestDto requestDto) {
       UserResponseDto responseDto = userService.updateUser(id, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    //xóa người dùng
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
            return ResponseEntity.noContent().build();
    }
}
