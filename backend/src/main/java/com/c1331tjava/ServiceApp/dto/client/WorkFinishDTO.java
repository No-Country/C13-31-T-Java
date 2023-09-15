package com.c1331tjava.ServiceApp.dto.client;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class WorkFinishDTO {

    @NotNull(message = "Comment must not be null")
    private String comment;
    @NotNull(message = "qualification must not be null")
    private Integer qualification;
    @Nullable
    private Set<String> images;

}
