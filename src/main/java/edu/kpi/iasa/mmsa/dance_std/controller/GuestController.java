package edu.kpi.iasa.mmsa.dance_std.controller;

import edu.kpi.iasa.mmsa.dance_std.model.Dance;
import edu.kpi.iasa.mmsa.dance_std.service.DanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GuestController {
    private final DanceService danceService;
    @Autowired
    public GuestController(DanceService danceService) {
        this.danceService = danceService;
    }
    @GetMapping(value = "/dance")
    public ResponseEntity<List<Dance>> getDances() {

        return ResponseEntity.ok(danceService.getDances());
    }
}
