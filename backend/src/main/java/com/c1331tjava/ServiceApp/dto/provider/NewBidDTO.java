package com.c1331tjava.ServiceApp.dto.provider;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NewBidDTO {
    @NotNull(message = "Response must not be null")
    private String response;
    private Float budget;
    private String comments;
}
