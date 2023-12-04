package com.wellid.exercise.inputs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PointInput
{
    @NotNull
    private Integer x;

    @NotNull
    private Integer y;
}
