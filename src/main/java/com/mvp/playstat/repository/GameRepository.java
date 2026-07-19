package com.mvp.playstat.repository;

import com.mvp.playstat.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
