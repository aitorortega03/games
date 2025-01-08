package com.aitorortegadev.games.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GameRequestDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;
}
