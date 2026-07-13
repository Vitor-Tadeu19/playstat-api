package com.mvp.playstat.service;

import com.mvp.playstat.dto.PlayerRequest;
import com.mvp.playstat.dto.PlayerResponse;
import com.mvp.playstat.dto.TeamSummaryResponse;
import com.mvp.playstat.exception.ResourceNotFoundException;
import com.mvp.playstat.model.Player;
import com.mvp.playstat.model.Team;
import com.mvp.playstat.repository.PlayerRepository;
import com.mvp.playstat.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerResponse create(PlayerRequest request) {
        Team team = findTeamByIdOrNull(request.teamId());

        Player player = Player.builder()
                .name(request.name())
                .age(request.age())
                .position(request.position())
                .height(request.height())
                .weight(request.weight())
                .pointsPerGame(request.pointsPerGame())
                .reboundsPerGame(request.reboundsPerGame())
                .assistsPerGame(request.assistsPerGame())
                .team(team)
                .build();

        Player savedPlayer = playerRepository.save(player);

        return toResponse(savedPlayer);
    }

    public List<PlayerResponse> findAll() {
        return playerRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public PlayerResponse findById(Long id) {
        Player player = findPlayerById(id);
        return toResponse(player);
    }

    public PlayerResponse update(Long id, PlayerRequest request) {
        Player player = findPlayerById(id);

        player.setName(request.name());
        player.setAge(request.age());
        player.setPosition(request.position());
        player.setHeight(request.height());
        player.setWeight(request.weight());
        player.setPointsPerGame(request.pointsPerGame());
        player.setReboundsPerGame(request.reboundsPerGame());
        player.setAssistsPerGame(request.assistsPerGame());

        Player updatedPlayer = playerRepository.save(player);

        return toResponse(updatedPlayer);
    }

    public void delete(Long id) {
        Player player = findPlayerById(id);
        playerRepository.delete(player);
    }

    private Player findPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + id));
    }

    private Team findTeamByIdOrNull(Long teamId) {
        if (teamId == null) {
            return null;
        }

        return teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + teamId));
    }

    private PlayerResponse toResponse(Player player) {
        return new PlayerResponse(
                player.getId(),
                player.getName(),
                player.getAge(),
                player.getPosition(),
                player.getHeight(),
                player.getWeight(),
                player.getPointsPerGame(),
                player.getReboundsPerGame(),
                player.getAssistsPerGame(),
                toTeamSummaryResponse(player.getTeam()),
                player.getCreatedAt(),
                player.getUpdatedAt()
        );
    }
    private TeamSummaryResponse toTeamSummaryResponse(Team team) {
        if (team == null) {
            return null;
        }

        return new TeamSummaryResponse(
                team.getId(),
                team.getName(),
                team.getCity()
        );
    }

}
