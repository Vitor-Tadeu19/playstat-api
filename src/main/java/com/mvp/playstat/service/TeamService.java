package com.mvp.playstat.service;

import com.mvp.playstat.dto.TeamRequest;
import com.mvp.playstat.dto.TeamResponse;
import com.mvp.playstat.exception.ResourceNotFoundException;
import com.mvp.playstat.model.Team;
import com.mvp.playstat.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamResponse create(TeamRequest request) {
        Team team = Team.builder()
                .name(request.name())
                .city(request.city())
                .coach(request.coach())
                .build();

        Team savedTeam = teamRepository.save(team);

        return toResponse(savedTeam);
    }

    public List<TeamResponse> findAll() {
        return teamRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TeamResponse findById(Long id) {
        Team team = findTeamById(id);
        return toResponse(team);
    }

    public TeamResponse update(Long id, TeamRequest request) {
        Team team = findTeamById(id);

        team.setName(request.name());
        team.setCity(request.city());
        team.setCoach(request.coach());

        Team updatedTeam = teamRepository.save(team);

        return toResponse(updatedTeam);
    }

    public void delete(Long id) {
        Team team = findTeamById(id);
        teamRepository.delete(team);
    }

    private Team findTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + id));
    }

    private TeamResponse toResponse(Team team) {
        return new TeamResponse(
                team.getId(),
                team.getName(),
                team.getCity(),
                team.getCoach(),
                team.getCreatedAt(),
                team.getUpdatedAt()
        );
    }
}
