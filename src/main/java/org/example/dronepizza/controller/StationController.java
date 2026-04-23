package org.example.dronepizza.controller;

import org.example.dronepizza.model.Station;
import org.example.dronepizza.service.StationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService){
        this.stationService = stationService;
    }


    @GetMapping
    public ResponseEntity<List<Station>> findAll(){
        return ResponseEntity.ok(stationService.findAll());
    }
}
