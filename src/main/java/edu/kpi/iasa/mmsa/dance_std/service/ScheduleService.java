package edu.kpi.iasa.mmsa.dance_std.service;

import edu.kpi.iasa.mmsa.dance_std.exception.GroupNotFoundException;
import edu.kpi.iasa.mmsa.dance_std.model.Schedule;
import edu.kpi.iasa.mmsa.dance_std.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> getSchedule(Long id) {
        return scheduleRepository.findAllByIdgroup(id).orElseThrow(GroupNotFoundException::new);
    }

}