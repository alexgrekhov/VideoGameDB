package com.techietester.controller;

import com.techietester.model.VideoGame;
import com.techietester.repository.VideoGameRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class VideoGameController {

    private final VideoGameRepository repository;

    public VideoGameController(VideoGameRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Get all games", description = "Retrieve all video games from the database")
    public List<VideoGame> getAllGames() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a game by ID")
    public VideoGame getGameById(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping
    @Operation(summary = "Add a new game")
    public String addGame(@RequestBody VideoGame game) {
        repository.save(game);
        return "Game added successfully!";
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing game")
    public String updateGame(@PathVariable int id, @RequestBody VideoGame game) {
        repository.update(id, game);
        return "Game updated successfully!";
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a game by ID")
    public String deleteGame(@PathVariable int id) {
        repository.delete(id);
        return "Game deleted successfully!";
    }
}
