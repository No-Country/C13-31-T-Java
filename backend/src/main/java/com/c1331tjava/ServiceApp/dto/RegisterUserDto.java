package com.c1331tjava.ServiceApp.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * A Data Transfer Object (DTO) representing the information needed to register a new user.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {


    @NotNull(message = "Name must not be null")
    private String userName;
    @NotNull(message = "Lastname must not be null")
    private String userLastname;
    @NotNull(message = "Password must not be null")
    private String password;
    @NotNull(message = "Email must not be null")
    private String email;
    @NotNull(message = "Birthdate must not be null")
    private Date birthDate;
    @NotNull(message = "Te must not be null")
    private String te;
    @Nullable
    private List<Long> areas;
    @NotEmpty(message = "The user must have at least 1 role")
    private List<Long> roles;
}

