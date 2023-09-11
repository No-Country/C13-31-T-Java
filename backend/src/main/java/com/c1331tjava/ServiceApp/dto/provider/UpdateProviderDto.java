package com.c1331tjava.ServiceApp.dto.provider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * A Data Transfer Object (DTO) representing the information needed to modify provider profile.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProviderDto {
    private String userName;
    private String userLastname;
    private String password;
    private String te;
    private List<String> areas;
}

