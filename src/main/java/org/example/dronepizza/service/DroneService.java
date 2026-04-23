package org.example.dronepizza.service;

import org.example.dronepizza.model.Delivery;
import org.example.dronepizza.model.Drone;
import org.example.dronepizza.model.Station;
import org.example.dronepizza.model.Status;
import org.example.dronepizza.repository.DeliveryRepository;
import org.example.dronepizza.repository.DroneRepository;
import org.example.dronepizza.repository.PizzaRepository;
import org.example.dronepizza.repository.StationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DroneService {
    private final DroneRepository droneRepository;
    private final DeliveryRepository deliveryRepository;
    private final StationRepository stationRepository;

    public DroneService(DroneRepository droneRepository,
                        DeliveryRepository deliveryRepository ,
                        StationRepository stationRepository){
        this.deliveryRepository = deliveryRepository;
        this.droneRepository = droneRepository;
        this.stationRepository = stationRepository;

    }

    public List<Drone> findAll(){
        return droneRepository.findAll();
    }

    public ResponseEntity<Drone> addDrone(){
        List<Station> stations = stationRepository.findAll();

        if (stations.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Station station = stations.stream()
                .min(Comparator.comparingInt(s-> s.getDrones().size()))
                .get();

        Drone newDrone = new Drone();
        newDrone.generateUUID();
        newDrone.setStatus(Status.I_DRIFT);
        newDrone.setStation(station);
        droneRepository.save(newDrone);

        return ResponseEntity.status(201).body(newDrone);
    }


}
