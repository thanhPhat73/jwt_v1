package com.example.jwt_v1.service;

import com.example.jwt_v1.domain.User;
import com.example.jwt_v1.dto.UpdateUserRequestDto;
import com.example.jwt_v1.dto.UserResponseDto;
import com.example.jwt_v1.repository.UserJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserJpaRepository userJpaRepository;

    public UserService(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    //chuyển user thành userResponseDto
    private UserResponseDto convertToDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    // tạo người dùng mới
    public UserResponseDto createUser(User newuser){
        User newUser = userJpaRepository.save(newuser);
        return convertToDto(newUser);
    }

    // lấy tất cả người dùng
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userJpaRepository.findAll();
        return users.stream().map(u -> convertToDto(u)).collect(Collectors.toList());
    }

    // lấy người dùng theo ID
    public UserResponseDto getUserById(Long id) {
        User user = userJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDto(user);
    }

    // cập nhật người dùng
    public UserResponseDto updateUser(Long id, UpdateUserRequestDto updateUserRequestDto) {
        User existingUser = userJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        // Cập nhật các thông tin của người dùng
        if (updateUserRequestDto.getName() != null) {
            existingUser.setName(updateUserRequestDto.getName());
        }
        if (updateUserRequestDto.getPassword() != null) {
            existingUser.setPassword(updateUserRequestDto.getPassword());
        }
        if (updateUserRequestDto.getEmail() != null) {
            existingUser.setEmail(updateUserRequestDto.getEmail());
        }

        // Lưu người dùng đã cập nhật
        User updatedUser = userJpaRepository.save(existingUser);
        return convertToDto(updatedUser);
    }

    // Xóa người dùng theo ID
    public void deleteUser(Long id) {
        User user = userJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userJpaRepository.delete(user);
    }

//    public User handleGetUserByUserName(String username) {
//        User data =  userJpaRepository.findAllByEmail(username);
//        return data;
//    }

    public UserResponseDto handleGetUserByUserName1(String username) {
        User data =  userJpaRepository.findAllByEmail(username);
        return convertToDto(data);
    }

}



