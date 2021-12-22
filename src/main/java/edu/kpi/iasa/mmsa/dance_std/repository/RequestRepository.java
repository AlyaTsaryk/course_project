package edu.kpi.iasa.mmsa.dance_std.repository;
import edu.kpi.iasa.mmsa.dance_std.model.Request;
import edu.kpi.iasa.mmsa.dance_std.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    Optional<List<Request>> findAllByIduser(Long iduser);
    Optional<List<Request>> findAllByStatus(Status status);
}
