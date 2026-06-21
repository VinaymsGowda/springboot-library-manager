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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }


}


