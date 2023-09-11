package com.c1331tjava.ServiceApp.dto.provider;

import com.c1331tjava.ServiceApp.model.Area;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ProviderDTO {
    private String imageLink;
    private String userName;
    private String userLastname;
    @NotNull(message = "Username must not be null")
    private String email;
    private String te;
    private List<Area> areas;
}
