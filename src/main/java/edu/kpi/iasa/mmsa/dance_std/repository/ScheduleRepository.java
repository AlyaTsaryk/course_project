package edu.kpi.iasa.mmsa.dance_std.repository;

import edu.kpi.iasa.mmsa.dance_std.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<List<Schedule>> findAllByIdgroup(Long idgroup);
}
