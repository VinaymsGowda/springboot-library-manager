package com.learning.librarysystem.controller;

import com.learning.librarysystem.advices.ApiResponse;
import com.learning.librarysystem.dto.User.LoginDto;
import com.learning.librarysystem.dto.User.SignUpDto;
import com.learning.librarysystem.dto.User.UserDto;
import com.learning.librarysystem.service.AuthService;
import com.learning.librarysystem.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthService userService;


    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody @Valid SignUpDto signUpDto) {
        UserDto userDto = userService.signUp(signUpDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginDto,
                                   HttpServletResponse response) {
        String token=userService.login(loginDto);
        ApiResponse<String> apiResponse = new ApiResponse<>(token);
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}

