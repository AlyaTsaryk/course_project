package edu.kpi.iasa.mmsa.dance_std.repository;
import edu.kpi.iasa.mmsa.dance_std.model.Dance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DanceRepository extends JpaRepository<Dance, Long> {
    Optional<Dance> findDanceByName(String name);
}

