package edu.kpi.iasa.mmsa.dance_std.repository;
import edu.kpi.iasa.mmsa.dance_std.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
    Optional<Hall> findHallById(Long id);
}
