package com.app.campionsl.repository;

import com.app.campionsl.models.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository <Match, Long>{
}
