package com.c1331tjava.ServiceApp.dto;

import com.c1331tjava.ServiceApp.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginResponseUserDTO {
    private String userName;
    private String lastName;
    private List<String> roles;
}
