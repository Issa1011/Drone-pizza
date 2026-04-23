package org.example.dronepizza.controller;

import org.example.dronepizza.model.Drone;
import org.example.dronepizza.model.Station;
import org.example.dronepizza.service.DeliveryService;
import org.example.dronepizza.service.DroneService;
import org.example.dronepizza.service.StationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/drones")
public class DroneController {
    private final DeliveryService deliveryService;
    private final DroneService droneService;
    private final StationService stationService;

    public DroneController(DeliveryService deliveryService,
                           DroneService droneService, StationService stationService){
        this.deliveryService = deliveryService;
        this.droneService = droneService;
        this.stationService = stationService;
    }

    @GetMapping
    public ResponseEntity<List<Drone>> findAll(){
        return ResponseEntity.ok(droneService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Drone> addDrone(){
        return droneService.addDrone();
    }


}
