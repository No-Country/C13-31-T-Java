package com.c1331tjava.ServiceApp.dto.provider;

import com.c1331tjava.ServiceApp.model.Area;
import lombok.Data;

import java.util.List;

@Data
public class ProviderDTO {
    private String imageLink;
    private String userName;
    private String userLastname;
    private String email;
    private String te;
    private List<Area> areas;
}
