package com.c1331tjava.ServiceApp.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Data Transfer Object (DTO) representing the information needed to modify client profile.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClientDto {
    private String userName;
    private String userLastname;
    private String password;
    private String te;
}

