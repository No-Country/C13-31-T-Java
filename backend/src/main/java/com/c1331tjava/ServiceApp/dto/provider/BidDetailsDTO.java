package com.c1331tjava.ServiceApp.dto.provider;

import lombok.Data;

import java.util.Date;

@Data
public class BidDetailsDTO {
    private Long id;
    private String response;
    private Float budget;
    private Date date;
    private String comments;

}
