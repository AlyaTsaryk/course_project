package edu.kpi.iasa.mmsa.dance_std.controller;

import edu.kpi.iasa.mmsa.dance_std.dto.GroupDto;
import edu.kpi.iasa.mmsa.dance_std.dto.RequestDto;
import edu.kpi.iasa.mmsa.dance_std.model.Account;
import edu.kpi.iasa.mmsa.dance_std.model.Group;
import edu.kpi.iasa.mmsa.dance_std.model.Request;
import edu.kpi.iasa.mmsa.dance_std.model.Schedule;
import edu.kpi.iasa.mmsa.dance_std.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static final String DEFAULT_STATUS = "Registered";
    private final RequestService requestService;
    private final UserDetailsServiceImpl userDetailsService;
    private final StatusService statusService;
    private final GroupService groupService;
    private final ScheduleService scheduleService;

    @Autowired
    public UserController(RequestService requestService, UserDetailsServiceImpl userDetailsService, StatusService statusService, GroupService groupService, ScheduleService scheduleService) {
        this.requestService = requestService;
        this.userDetailsService = userDetailsService;
        this.statusService = statusService;
        this.groupService = groupService;
        this.scheduleService = scheduleService;
    }
    @GetMapping(value = "/group")
    public ResponseEntity<List<Group>> getGroups() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Account account = userDetailsService.loadUserByUsername(username).getAccount();
        List<Group> groups = groupService.getGroups();
        List<Group> my_groups = new ArrayList<>();
        for (Group group : groups) {
            if (group.getUsers().contains(account)) {
                my_groups.add(group);
            }
        }
        return ResponseEntity.ok(my_groups);
    }
    @GetMapping(value = "/groups")
    public ResponseEntity<List<Group>> getAllGroups() {
        return ResponseEntity.ok(groupService.getGroups());
    }
    @GetMapping(value = "/schedule")
    public ResponseEntity<List<List<Schedule>>> getMySchedule() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Account account = userDetailsService.loadUserByUsername(username).getAccount();
        List<Group> groups = groupService.getGroups();
        List<Group> my_groups = new ArrayList<>();
        for (Group group : groups) {
            if (group.getUsers().contains(account)) {
                my_groups.add(group);
            }
        }
        List<List<Schedule>> sch = new ArrayList<>();
        my_groups.forEach(gr -> sch.add(scheduleService.getSchedule(gr.getId())));
        return ResponseEntity.ok(sch);
    }
    @GetMapping(value = "/request")
    public ResponseEntity<List<Request>> getRequests() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return ResponseEntity.ok(requestService.getRequests(userDetailsService.loadUserByUsername(username).getAccount().getId()));
    }
    @PostMapping(value = "/request")
    public ResponseEntity<GroupDto> postRequest(@Valid @RequestBody RequestDto requestDto) {
        Request request = createRequest(requestDto);
        return ResponseEntity.ok(createRequestDto(requestService.createRequest(request)));
    }
    private GroupDto createRequestDto(Request request) {
        return new GroupDto(request.getId_group());
    }
    private Request createRequest(RequestDto requestDt) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Request request = Request.builder()
                .id_group(requestDt.getId_group())
                .build();
        request.setIduser(userDetailsService.loadUserByUsername(username).getAccount().getId());
        request.setStatus(statusService.getStatusByName(DEFAULT_STATUS));
        return request;
    }
}


