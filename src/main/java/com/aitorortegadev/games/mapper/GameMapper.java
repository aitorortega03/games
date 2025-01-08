package com.aitorortegadev.games.mapper;

import com.aitorortegadev.games.model.dto.GameRequestDTO;
import com.aitorortegadev.games.model.dto.GameResponseDTO;
import com.aitorortegadev.games.model.entity.Game;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GameMapper {

    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

    Game toEntity(GameRequestDTO dto);

    GameResponseDTO toResponse(Game game);
}
