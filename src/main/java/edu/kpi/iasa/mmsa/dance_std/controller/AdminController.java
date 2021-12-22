package edu.kpi.iasa.mmsa.dance_std.controller;

import edu.kpi.iasa.mmsa.dance_std.model.Group;
import edu.kpi.iasa.mmsa.dance_std.model.Hall;
import edu.kpi.iasa.mmsa.dance_std.model.Request;
import edu.kpi.iasa.mmsa.dance_std.model.Status;
import edu.kpi.iasa.mmsa.dance_std.service.GroupService;
import edu.kpi.iasa.mmsa.dance_std.service.HallService;
import edu.kpi.iasa.mmsa.dance_std.service.RequestService;
import edu.kpi.iasa.mmsa.dance_std.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {


    private final StatusService statusService;
    private final RequestService requestService;
    private final GroupService groupService;
    private final HallService hallService;
    @Autowired
    public AdminController(StatusService statusService, RequestService requestService, GroupService groupService, HallService hallService) {
        this.statusService = statusService;
        this.requestService = requestService;
        this.groupService = groupService;
        this.hallService = hallService;
    }
    @GetMapping(value = "/status")
    public ResponseEntity<List<Status>> getStatuses() {

        return ResponseEntity.ok(statusService.getStatuses());
    }

    @PostMapping(value = "/status")
    public ResponseEntity<Status> postStatuses(@Valid @RequestBody Status newStatus) {
        return ResponseEntity.ok(statusService.saveStatus(newStatus));
    }

    @GetMapping(value = "/status/{id}")
    public ResponseEntity<Status> getStatus(@PathVariable Long id) {
        return ResponseEntity.ok(statusService.getStatusById(id));
    }

    @PutMapping(value = "/status/{id}")
    public ResponseEntity<Status> updateStatus(@PathVariable Long id, @Valid @RequestBody Status updatedStatus) {
        return ResponseEntity.ok(statusService.updateStatusById(id, updatedStatus));
    }

    @DeleteMapping(value = "/status/{id}")
    public ResponseEntity<String> deleteStatus(@PathVariable Long id) {
        return ResponseEntity.ok(statusService.deleteStatusById(id));
    }

    @GetMapping(value = "/request")
    public ResponseEntity<Optional<List<Request>>> getRequests() {
        return ResponseEntity.ok(requestService.getAllRegRequests("Registered"));
    }
    @PutMapping(value = "/request/{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable Long id, @Valid @RequestBody Request updatedRequest) {
        Request request = requestService.updateRequestById(id, updatedRequest);
        if (request.getStatus().getName().equals("Accepted")) {
            groupService.updateGroupById(request.getId_group(), request.getIduser());
        }
        return ResponseEntity.ok(request);
    }
    @GetMapping(value = "/group/{id}")
    public ResponseEntity<Group> getGroup(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.getGroupById(id));
    }
    @GetMapping(value = "/group/capacity/{id}")
    public ResponseEntity<Integer> getCapacity(@PathVariable Long id) {
        Group group = groupService.getGroupById(id);
        return ResponseEntity.ok(group.getUsers().size());
    }
    @GetMapping(value = "/hall")
    public ResponseEntity<List<Hall>> getHall() {
        return ResponseEntity.ok(hallService.getHalls());
    }
}
