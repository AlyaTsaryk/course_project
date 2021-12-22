package edu.kpi.iasa.mmsa.dance_std.service;

import edu.kpi.iasa.mmsa.dance_std.model.Dance;
import edu.kpi.iasa.mmsa.dance_std.repository.DanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanceService {
    private final DanceRepository danceRepository;

    public DanceService(DanceRepository danceRepository) {
        this.danceRepository = danceRepository;
    }

    public List<Dance> getDances() {
        return danceRepository.findAll();
    }

}
