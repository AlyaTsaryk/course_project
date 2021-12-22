package edu.kpi.iasa.mmsa.dance_std.controller;
import edu.kpi.iasa.mmsa.dance_std.model.Group;
import edu.kpi.iasa.mmsa.dance_std.model.Schedule;
import edu.kpi.iasa.mmsa.dance_std.model.Status;
import edu.kpi.iasa.mmsa.dance_std.service.GroupService;
import edu.kpi.iasa.mmsa.dance_std.service.ScheduleService;
import edu.kpi.iasa.mmsa.dance_std.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/trainer")
public class TrainerController {
    private final GroupService groupService;
    private final ScheduleService scheduleService;
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public TrainerController(GroupService groupService, ScheduleService scheduleService, UserDetailsServiceImpl userDetailsService) {
        this.groupService = groupService;
        this.scheduleService = scheduleService;
        this.userDetailsService = userDetailsService;
    }
    @GetMapping(value = "/group")
    public ResponseEntity<List<Group>> getGroups() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return ResponseEntity.ok(groupService.getGroup(userDetailsService.loadUserByUsername(username).getAccount().getId()));
    }
    @GetMapping(value = "/group/{id}")
    public ResponseEntity<Group> getGroups(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.getGroupById(id));
    }


    @GetMapping(value = "/schedule")
    public ResponseEntity<List<List<Schedule>>> getSchedule() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        List<Group> group = groupService.getGroup(userDetailsService.loadUserByUsername(username).getAccount().getId());
        List<List<Schedule>> sch = new ArrayList<>();
        group.forEach(gr -> sch.add(scheduleService.getSchedule(gr.getId())));
        return ResponseEntity.ok(sch);
    }
}
