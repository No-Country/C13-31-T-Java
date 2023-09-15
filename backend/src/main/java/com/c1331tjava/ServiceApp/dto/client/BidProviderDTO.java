package com.c1331tjava.ServiceApp.dto.client;

import com.c1331tjava.ServiceApp.model.Area;
import lombok.Data;
import java.util.List;

@Data
public class BidProviderDTO {
    private Long id;
    private String imageLink;
    private String userName;
    private String userLastname;
    private List<Area> areas;
}
