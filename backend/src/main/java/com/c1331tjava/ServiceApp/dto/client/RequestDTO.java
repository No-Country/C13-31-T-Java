package com.c1331tjava.ServiceApp.dto.client;

import com.c1331tjava.ServiceApp.dto.BidDTO;
import com.c1331tjava.ServiceApp.model.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
public class RequestDTO {
    private Long id;
    private LocalDate date;
    private Zone zone;
    private String description;
    private Set<ImagesR> images;
    private Set<BidDTO> bids;
    private String comments;
    private Boolean ended;
}
