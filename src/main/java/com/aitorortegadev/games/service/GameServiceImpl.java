package com.aitorortegadev.games.service;

import com.aitorortegadev.games.model.Game;
import com.aitorortegadev.games.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game getGameById(Long id) {
        return gameRepository.findById(id).get();
    }

    @Override
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game updateGame(Long id, Game game) {
        Optional<Game> existingGame = gameRepository.findById(id);
        return existingGame.isPresent() ? gameRepository.save(game) : null;
    }

    @Override
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}
