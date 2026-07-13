package com.mvp.playstat.repository;

import com.mvp.playstat.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
