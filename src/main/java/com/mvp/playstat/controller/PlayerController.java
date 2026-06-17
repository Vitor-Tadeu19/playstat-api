package com.mvp.playstat.controller;

import com.mvp.playstat.dto.PlayerRequest;
import com.mvp.playstat.dto.PlayerResponse;
import com.mvp.playstat.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerResponse create(@RequestBody @Valid PlayerRequest request) {
        return playerService.create(request);
    }

    @GetMapping
    public List<PlayerResponse> findAll() {
        return playerService.findAll();
    }

    @GetMapping("/{id}")
    public PlayerResponse findById(@PathVariable Long id) {
        return playerService.findById(id);
    }

    @PutMapping("/{id}")
    public PlayerResponse update(
            @PathVariable Long id,
            @RequestBody @Valid PlayerRequest request
    ) {
        return playerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        playerService.delete(id);
    }
}
