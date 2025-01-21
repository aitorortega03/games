package com.aitorortegadev.games.controller;

import com.aitorortegadev.games.model.dto.GameRequestDTO;
import com.aitorortegadev.games.model.dto.GameResponseDTO;
import com.aitorortegadev.games.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameResponseDTO>> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponseDTO> getGame(@PathVariable String id) {
        return gameService.getGameById(Long.parseLong(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GameResponseDTO> createNewGame(@RequestHeader("userIdRequest") String userId,
                                                         @RequestBody GameRequestDTO newGame) {
        return ResponseEntity.ok(gameService.createGame(newGame));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameResponseDTO> updateGame(@PathVariable String id, @RequestBody GameRequestDTO updatedGame) {
        try {
            GameResponseDTO updatedGameDTO = gameService.updateGame(Long.parseLong(id), updatedGame);
            return ResponseEntity.ok(updatedGameDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable String id) {
        gameService.deleteGame(Long.parseLong(id));
        return ResponseEntity.noContent().build();
    }

}
