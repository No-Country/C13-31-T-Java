package com.c1331tjava.ServiceApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private LoginResponseUserDTO user;

}
