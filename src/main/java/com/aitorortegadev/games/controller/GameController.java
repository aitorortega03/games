package com.aitorortegadev.games.controller;

import com.aitorortegadev.games.model.Game;
import com.aitorortegadev.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        return new ResponseEntity<>(gameService.getAllGames(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable String id) {
        return new ResponseEntity<>(gameService.getGameById(Long.parseLong(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Game> createNewGame(@RequestBody Game newGame) {
        return new ResponseEntity<>(gameService.createGame(newGame), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable String id, @RequestBody Game updatedGame) {
        return new ResponseEntity<>(gameService.updateGame(Long.parseLong(id), updatedGame), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable String id) {
        gameService.deleteGame(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
