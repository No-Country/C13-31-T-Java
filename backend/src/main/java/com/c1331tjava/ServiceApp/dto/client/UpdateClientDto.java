package com.c1331tjava.ServiceApp.dto.client;

import lombok.Data;

/**
 * A Data Transfer Object (DTO) representing the information needed to modify client profile.
 */
@Data
public class UpdateClientDto {
    private String imageLink;
    private String userName;
    private String userLastname;
    private String password;
    private String te;
}

