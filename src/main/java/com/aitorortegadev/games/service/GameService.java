package com.aitorortegadev.games.service;

import com.aitorortegadev.games.model.entity.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

    List<Game> getAllGames();

    Optional<Game> getGameById(Long id);

    Game createGame(Game game);

    Game updateGame(Long id, Game game);

    void deleteGame(Long id);




}
