package com.c1331tjava.ServiceApp.dto.provider;

import com.c1331tjava.ServiceApp.model.Bid;
import com.c1331tjava.ServiceApp.model.ImagesR;
import com.c1331tjava.ServiceApp.model.Zone;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class RequestProviderDTO {
    private Long id;
    private LocalDate date;
    private Zone zone;
    private String description;
    private Set<ImagesR> images;
    private String comments;
    private Boolean ended;
}
