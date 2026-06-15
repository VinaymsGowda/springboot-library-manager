package com.learning.librarysystem.dto.Author;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAuthorDto {

    @NotBlank
    private String firstName;
    private String lastName;

    @NotBlank
    @Email
    private String email;


}
