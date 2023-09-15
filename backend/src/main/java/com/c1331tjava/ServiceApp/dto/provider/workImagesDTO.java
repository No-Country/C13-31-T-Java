package com.c1331tjava.ServiceApp.dto.provider;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class workImagesDTO {
    @NotNull(message = "Images must not be null")
    private Set<String> images;
}
