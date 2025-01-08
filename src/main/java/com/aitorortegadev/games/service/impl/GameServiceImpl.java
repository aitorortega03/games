package com.aitorortegadev.games.service.impl;

import com.aitorortegadev.games.model.entity.Game;
import com.aitorortegadev.games.repository.GameRepository;
import com.aitorortegadev.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game updateGame(Long id, Game game) {
        if (!gameRepository.existsById(id)){
            return gameRepository.save(game);
        }
        return gameRepository.findById(id)
                .map(g -> {
                    g.setName(game.getName());
                    return gameRepository.save(g);
                })
                .get();
    }

    @Override
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}
