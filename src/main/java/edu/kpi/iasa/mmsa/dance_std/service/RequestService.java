package edu.kpi.iasa.mmsa.dance_std.service;
import edu.kpi.iasa.mmsa.dance_std.exception.RequestNotFoundException;
import edu.kpi.iasa.mmsa.dance_std.exception.RoleNotFoundException;
import edu.kpi.iasa.mmsa.dance_std.exception.StatusNotFoundException;
import edu.kpi.iasa.mmsa.dance_std.model.Request;
import edu.kpi.iasa.mmsa.dance_std.model.Status;
import edu.kpi.iasa.mmsa.dance_std.repository.RequestRepository;
import edu.kpi.iasa.mmsa.dance_std.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    private final RequestRepository requestRepository;
    private final StatusRepository statusRepository;

    public RequestService(RequestRepository requestRepository, StatusRepository statusRepository) {
        this.requestRepository = requestRepository;
        this.statusRepository = statusRepository;
    }
    public List<Request> getRequests(Long id) {return requestRepository.findAllByIduser(id).orElseThrow(RequestNotFoundException::new);}
    public Request saveRequest(Request newRequest) {
        return requestRepository.save(newRequest);
    }
    public Request createRequest(Request request) {
        return requestRepository.save(request);
    }
    public List<Request> getAllRequests() {return requestRepository.findAll();}
    public Optional<List<Request>> getAllRegRequests(String name) {return requestRepository.findAllByStatus(statusRepository.findStatusByName(name).orElseThrow(RequestNotFoundException::new));}
    public Request getRequestById(Long id) {
        Optional<Request> request = requestRepository.findById(id);
        if (request.isPresent()) {
            return request.get();
        }
        throw new StatusNotFoundException();
    }

    public Request updateRequestById(Long id, Request updatedRequest) {
        Optional<Request> request = requestRepository.findById(id);
        if (request.isPresent()) {
            Request oldRequest = request.get();
            updateRequest(oldRequest, updatedRequest);
            return requestRepository.save(oldRequest);
        }
        throw new StatusNotFoundException();
    }

    private void updateRequest(Request oldRequest, Request updatedRequest) {
        oldRequest.setStatus(updatedRequest.getStatus());
    }
}