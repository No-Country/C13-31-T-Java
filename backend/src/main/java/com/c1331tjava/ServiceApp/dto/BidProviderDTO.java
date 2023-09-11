package com.c1331tjava.ServiceApp.dto;

import com.c1331tjava.ServiceApp.model.Area;
import com.c1331tjava.ServiceApp.model.Notification;
import com.c1331tjava.ServiceApp.model.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BidProviderDTO {
    private Long id;
    private String imageLink;
    private String userName;
    private String userLastname;
    private List<Area> areas;
}
