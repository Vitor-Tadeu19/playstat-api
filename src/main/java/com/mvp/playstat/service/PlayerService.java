package com.mvp.playstat.service;

import com.mvp.playstat.dto.PlayerRequest;
import com.mvp.playstat.dto.PlayerResponse;
import com.mvp.playstat.exception.ResourceNotFoundException;
import com.mvp.playstat.model.Player;
import com.mvp.playstat.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerResponse create(PlayerRequest request) {
        Player player = Player.builder()
                .name(request.name())
                .age(request.age())
                .position(request.position())
                .height(request.height())
                .weight(request.weight())
                .pointsPerGame(request.pointsPerGame())
                .reboundsPerGame(request.reboundsPerGame())
                .assistsPerGame(request.assistsPerGame())
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
                player.getCreatedAt(),
                player.getUpdatedAt()
        );
    }
}
