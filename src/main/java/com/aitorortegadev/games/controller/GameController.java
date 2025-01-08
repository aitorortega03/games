package com.aitorortegadev.games.controller;

import com.aitorortegadev.games.mapper.GameMapper;
import com.aitorortegadev.games.model.dto.GameRequestDTO;
import com.aitorortegadev.games.model.dto.GameResponseDTO;
import com.aitorortegadev.games.model.entity.Game;
import com.aitorortegadev.games.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    private final GameService gameService;

    private final GameMapper gameMapper;

    public GameController(GameService gameService, GameMapper gameMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }

    @GetMapping
    public ResponseEntity<List<GameResponseDTO>> getAllGames() {
        List<GameResponseDTO> responseList =
                gameService.getAllGames()
                        .stream()
                        .map(gameMapper::toResponse)
                        .toList();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponseDTO> getGame(@PathVariable String id) {
        GameResponseDTO gameResponseDTO = gameService.getGameById(Long.parseLong(id))
                        .map(gameMapper::toResponse)
                        .orElse(null);
        return Objects.isNull(gameResponseDTO)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(gameResponseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Game> createNewGame(@RequestBody GameRequestDTO newGame) {
        return new ResponseEntity<>(gameService.createGame(gameMapper.toEntity(newGame)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable String id, @RequestBody GameRequestDTO updatedGame) {
        GameResponseDTO gameResponseDTO = gameService.getGameById(Long.parseLong(id))
                        .map(gameMapper::toResponse)
                        .orElse(null);
        if (Objects.isNull(gameResponseDTO)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (gameResponseDTO.getName().equals(updatedGame.getName())){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(gameService.updateGame(Long.parseLong(id), gameMapper.toEntity(updatedGame)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable String id) {
        GameResponseDTO gameResponseDTO = gameService.getGameById(Long.parseLong(id))
                        .map(gameMapper::toResponse)
                        .orElse(null);

        if (Objects.isNull(gameResponseDTO)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        gameService.deleteGame(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
