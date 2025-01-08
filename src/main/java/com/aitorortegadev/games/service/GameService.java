package com.aitorortegadev.games.service;

import com.aitorortegadev.games.model.Game;

import java.util.List;

public interface GameService {

    List<Game> getAllGames();

    Game getGameById(Long id);

    Game createGame(Game game);

    Game updateGame(Long id, Game game);

    void deleteGame(Long id);




}
