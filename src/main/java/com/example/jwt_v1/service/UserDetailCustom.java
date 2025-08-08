package com.example.jwt_v1.service;

import com.example.jwt_v1.dto.UserResponseDto;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

// để thông báo đang viết hàm custom ghi đè thằng UserDetailsService
// dùng anition sau (gián tiếp) ghi đè bằng tên của bean
@Component("userDetailsServices")

// cách khác ghi trực tiếp vào SecurityConfiguartion
//authProvider.setUserDetailsService(userDetailsServices)
public class UserDetailCustom implements UserDetailsService {

    private UserService userService;

    public UserDetailCustom(UserService userService) {
        this.userService = userService;
    }
    @Override
    //username ở daay chính là cái mình truyền lên dùng để query user = cái username
    //sau khi lấy lên đc thông tin người dùng (vd: username và password ) => cần decode password để ss vs thang pass gưi rleen

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //    com.example.jwt_v1.domain.User user = this.userService.handleGetUserByUserName(username);
        UserResponseDto user = this.userService.handleGetUserByUserName1(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username/password không hợp lệ");
        }

        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

    }
}
