package com.mvp.playstat.service;

import com.mvp.playstat.dto.GameRequest;
import com.mvp.playstat.dto.GameResponse;
import com.mvp.playstat.dto.TeamSummaryResponse;
import com.mvp.playstat.exception.BusinessException;
import com.mvp.playstat.exception.ResourceNotFoundException;
import com.mvp.playstat.model.Game;
import com.mvp.playstat.model.Team;
import com.mvp.playstat.model.enums.GameStatus;
import com.mvp.playstat.repository.GameRepository;
import com.mvp.playstat.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;

    public GameResponse create(GameRequest request) {
        validateGameRequest(request);

        Team homeTeam = findTeamById(request.homeTeamId());
        Team awayTeam = findTeamById(request.awayTeamId());

        Game game = Game.builder()
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .gameDate(request.gameDate())
                .homeScore(request.homeScore())
                .awayScore(request.awayScore())
                .status(request.status() != null ? request.status() : GameStatus.AGENDADO)
                .build();

        Game savedGame = gameRepository.save(game);

        return toResponse(savedGame);
    }

    public List<GameResponse> findAll() {
        return gameRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public GameResponse findById(Long id) {
        Game game = findGameById(id);
        return toResponse(game);
    }

    public GameResponse update(Long id, GameRequest request) {
        validateGameRequest(request);

        Game game = findGameById(id);

        Team homeTeam = findTeamById(request.homeTeamId());
        Team awayTeam = findTeamById(request.awayTeamId());

        game.setHomeTeam(homeTeam);
        game.setAwayTeam(awayTeam);
        game.setGameDate(request.gameDate());
        game.setHomeScore(request.homeScore());
        game.setAwayScore(request.awayScore());
        game.setStatus(request.status() != null ? request.status() : GameStatus.AGENDADO);

        Game updatedGame = gameRepository.save(game);

        return toResponse(updatedGame);
    }

    public void delete(Long id) {
        Game game = findGameById(id);
        gameRepository.delete(game);
    }

    private void validateGameRequest(GameRequest request) {
        if (request.homeTeamId().equals(request.awayTeamId())) {
            throw new BusinessException("Home team and away team must be different");
        }

        GameStatus status = request.status() != null ? request.status() : GameStatus.AGENDADO;

        if (status == GameStatus.FINALIZADO) {
            if (request.homeScore() == null || request.awayScore() == null) {
                throw new BusinessException("Finished games must have home score and away score");
            }
        }

        if (status == GameStatus.AGENDADO) {
            if (request.homeScore() != null || request.awayScore() != null) {
                throw new BusinessException("Scheduled games should not have scores");
            }
        }
    }

    private Game findGameById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found with id: " + id));
    }

    private Team findTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + id));
    }

    private GameResponse toResponse(Game game) {
        return new GameResponse(
                game.getId(),
                toTeamSummaryResponse(game.getHomeTeam()),
                toTeamSummaryResponse(game.getAwayTeam()),
                game.getGameDate(),
                game.getHomeScore(),
                game.getAwayScore(),
                game.getStatus(),
                game.getCreatedAt(),
                game.getUpdatedAt()
        );
    }

    private TeamSummaryResponse toTeamSummaryResponse(Team team) {
        return new TeamSummaryResponse(
                team.getId(),
                team.getName(),
                team.getCity()
        );
    }
}
