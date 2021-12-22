package edu.kpi.iasa.mmsa.dance_std.service;

import edu.kpi.iasa.mmsa.dance_std.exception.RoleNotFoundException;
import edu.kpi.iasa.mmsa.dance_std.exception.StatusNotFoundException;
import edu.kpi.iasa.mmsa.dance_std.model.Role;
import edu.kpi.iasa.mmsa.dance_std.model.Status;
import edu.kpi.iasa.mmsa.dance_std.repository.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StatusService {
    private final StatusRepository statusRepository;
    public StatusService(StatusRepository statusRepository){
        this.statusRepository = statusRepository;
    }

    public List<Status> getStatuses() {
        return statusRepository.findAll();
    }

    public Status saveStatus(Status newStatus) {
        return statusRepository.save(newStatus);
    }

    public Status getStatusByName(String name) {
        return statusRepository.findStatusByName(name).orElseThrow(StatusNotFoundException::new);
    }



    public Status getStatusById(Long id) {
        Optional<Status> status = statusRepository.findById(id);
        if (status.isPresent()) {
            return status.get();
        }
        throw new StatusNotFoundException();
    }

    public Status updateStatusById(Long id, Status updatedStatus) {
        Optional<Status> status = statusRepository.findById(id);
        if (status.isPresent()) {
            Status oldStatus = status.get();
            log.info("status: {}", oldStatus);
            updateStatus(oldStatus, updatedStatus);
            return statusRepository.save(oldStatus);
        }
        throw new StatusNotFoundException();
    }

    private void updateStatus(Status oldStatus, Status updatedStatus) {
        oldStatus.setName(updatedStatus.getName());
    }

    public String deleteStatusById(Long id) {
        statusRepository.deleteById(id);
        return "Status was successfully deleted!";
    }
}
