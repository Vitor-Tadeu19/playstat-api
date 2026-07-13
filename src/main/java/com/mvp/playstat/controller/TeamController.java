package com.mvp.playstat.controller;

import com.mvp.playstat.dto.TeamRequest;
import com.mvp.playstat.dto.TeamResponse;
import com.mvp.playstat.service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponse create(@RequestBody @Valid TeamRequest request) {
        return teamService.create(request);
    }

    @GetMapping
    public List<TeamResponse> findAll() {
        return teamService.findAll();
    }

    @GetMapping("/{id}")
    public TeamResponse findById(@PathVariable Long id) {
        return teamService.findById(id);
    }

    @PutMapping("/{id}")
    public TeamResponse update(
            @PathVariable Long id,
            @RequestBody @Valid TeamRequest request
    ) {
        return teamService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        teamService.delete(id);
    }
}