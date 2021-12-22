package edu.kpi.iasa.mmsa.dance_std.service;

import edu.kpi.iasa.mmsa.dance_std.model.Hall;
import edu.kpi.iasa.mmsa.dance_std.model.Status;
import edu.kpi.iasa.mmsa.dance_std.repository.HallRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {
    private final HallRepository hallRepository;

    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }
    public List<Hall> getHalls() {
        return hallRepository.findAll();
    }
}