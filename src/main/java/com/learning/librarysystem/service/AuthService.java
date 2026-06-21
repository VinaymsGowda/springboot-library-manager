package com.learning.librarysystem.service;

import com.learning.librarysystem.dto.User.LoginDto;
import com.learning.librarysystem.dto.User.SignUpDto;
import com.learning.librarysystem.dto.User.UserDto;
import com.learning.librarysystem.entity.User;
import com.learning.librarysystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public UserDto signUp(SignUpDto signUpDto) {
        // Check if password and confirm password match
        if (!signUpDto.getPassword().equals(signUpDto.getConfirmPassword())) {
            throw new IllegalArgumentException("Password and Confirm Password must match");
        }

        // Check if user already exists
        if (userRepository.findByUserName(signUpDto.getUserName()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepository.findByEmail(signUpDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Create new user
        User user = new User();
        user.setUserName(signUpDto.getUserName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    public String login(LoginDto loginDto) {
        // Find user by username or email
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword())
        );

        System.out.println(authentication);
        System.out.println("Principal: " + authentication.getPrincipal());

        if(!authentication.isAuthenticated()){
            throw new BadCredentialsException("Bad Credentials");
        }


        UserDto user = modelMapper.map(authentication.getPrincipal(),UserDto.class);

        return jwtService.generateToken(user);



    }
}
