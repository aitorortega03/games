package com.aitorortegadev.games.service;

import com.aitorortegadev.games.model.dto.GameRequestDTO;
import com.aitorortegadev.games.model.dto.GameResponseDTO;

import java.util.List;
import java.util.Optional;

public interface GameService {

    List<GameResponseDTO> getAllGames();

    Optional<GameResponseDTO> getGameById(Long id);

    GameResponseDTO createGame(GameRequestDTO game);

    GameResponseDTO updateGame(Long id, GameRequestDTO game);

    void deleteGame(Long id);




}
