package com.c1331tjava.ServiceApp.dto.client;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Set;

@Data
public class RequestSaveDTO {
    @NotNull (message = "Zone must not be null")
    private String zoneDTO;
    @NotNull (message = "Description must not be null")
    private String description;
    private String comments;
    @Nullable
    private Set<String> imagesDTO;
}
