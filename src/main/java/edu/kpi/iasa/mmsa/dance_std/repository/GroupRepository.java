package edu.kpi.iasa.mmsa.dance_std.repository;
import edu.kpi.iasa.mmsa.dance_std.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findAllByIdtrain(Long trainer);
    Optional<Group> findGroupById(Long id);
}
