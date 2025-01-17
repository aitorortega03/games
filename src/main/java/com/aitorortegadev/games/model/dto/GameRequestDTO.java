package com.aitorortegadev.games.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameRequestDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;
}
