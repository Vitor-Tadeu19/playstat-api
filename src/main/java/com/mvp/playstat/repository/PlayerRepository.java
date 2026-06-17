package com.mvp.playstat.repository;

import com.mvp.playstat.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
