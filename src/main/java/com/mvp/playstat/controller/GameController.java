package com.mvp.playstat.controller;

import com.mvp.playstat.dto.GameRequest;
import com.mvp.playstat.dto.GameResponse;
import com.mvp.playstat.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameResponse create(@RequestBody @Valid GameRequest request) {
        return gameService.create(request);
    }

    @GetMapping
    public List<GameResponse> findAll() {
        return gameService.findAll();
    }

    @GetMapping("/{id}")
    public GameResponse findById(@PathVariable Long id) {
        return gameService.findById(id);
    }

    @PutMapping("/{id}")
    public GameResponse update(
            @PathVariable Long id,
            @RequestBody @Valid GameRequest request
    ) {
        return gameService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        gameService.delete(id);
    }
}
