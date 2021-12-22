package edu.kpi.iasa.mmsa.dance_std.repository;

import edu.kpi.iasa.mmsa.dance_std.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findStatusByName(String name);
}
