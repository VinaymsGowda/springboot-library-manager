package com.learning.librarysystem.dto.User;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private UUID id;
    private String userName;
    private String email;
}

