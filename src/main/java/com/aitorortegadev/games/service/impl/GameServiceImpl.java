package com.aitorortegadev.games.service.impl;

import com.aitorortegadev.games.model.dto.GameRequestDTO;
import com.aitorortegadev.games.model.dto.GameResponseDTO;
import com.aitorortegadev.games.model.entity.Game;
import com.aitorortegadev.games.repository.GameRepository;
import com.aitorortegadev.games.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Override
    public List<GameResponseDTO> getAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public Optional<GameResponseDTO> getGameById(Long id) {
        return gameRepository.findById(id)
                .map(this::toResponse);
    }

    @Override
    public GameResponseDTO createGame(GameRequestDTO game) {
        Game newGame = this.toEntity(game);
        Game savedGame = gameRepository.save(newGame);
        return this.toResponse(savedGame);
    }

    @Override
    public GameResponseDTO updateGame(Long id, GameRequestDTO game) {
        return gameRepository.findById(id)
                .map(g -> {
                    g.setName(game.getName());
                    return gameRepository.save(g);
                })
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + id));
    }

    @Override
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    private Game toEntity(GameRequestDTO game) {
        return Game.builder()
          .name(game.getName())
          .build();
    }

    private GameResponseDTO toResponse(Game game) {
        return GameResponseDTO.builder()
          .id(game.getId())
          .name(game.getName())
          .build();
    }
}
