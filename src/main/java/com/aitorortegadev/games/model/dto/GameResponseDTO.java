package com.aitorortegadev.games.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameResponseDTO {

    private Long id;
    private String name;

}
