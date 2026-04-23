package org.example.dronepizza.controller;

import org.example.dronepizza.model.Drone;
import org.example.dronepizza.model.Station;
import org.example.dronepizza.service.DeliveryService;
import org.example.dronepizza.service.DroneService;
import org.example.dronepizza.service.StationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}/enable")
    public ResponseEntity<Drone> enableDrone(@PathVariable Long id){
        return droneService.enableDrone(id);
    }

    @PutMapping("/{id}/disable")
    public ResponseEntity<Drone> disableDrone(@PathVariable Long id){
        return droneService.disableDrone(id);
    }

    @PutMapping("/{id}/retire")
    public ResponseEntity<Drone> retireDrone(@PathVariable Long id){
        return droneService.retireDrone(id);
    }


}
