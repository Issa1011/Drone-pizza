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


    // metode der ændre status til ny status på en drone;
    public ResponseEntity<Drone> enableDrone(Long id) {
        Drone drone = droneRepository.findById(id).orElse(null);

        if (drone == null) {
            return ResponseEntity.notFound().build();    //404
        }
        if (drone.getStatus() == Status.I_DRIFT) {
            return ResponseEntity.badRequest().build();          //400
        }

        drone.setStatus(Status.I_DRIFT);
        droneRepository.save(drone);

        return ResponseEntity.ok(drone);        //200

    }
    

    public ResponseEntity<Drone> disableDrone(Long id){
        Drone drone = droneRepository.findById(id).orElse(null);

        if(drone == null){
            return ResponseEntity.notFound().build();
        }

        if (drone.getStatus() == Status.UD_AF_DRIFT){
            return ResponseEntity.badRequest().build();
        }

        drone.setStatus(Status.UD_AF_DRIFT);
        droneRepository.save(drone);


        return ResponseEntity.ok(drone);
    }

    public ResponseEntity<Drone> retireDrone(Long id){
        Drone drone = droneRepository.findById(id).orElse(null);

        if (drone == null){
            return ResponseEntity.notFound().build();
        }
        if (drone.getStatus() == Status.UDFASET){
            return ResponseEntity.badRequest().build();
        }

        drone.setStatus(Status.UDFASET);
        droneRepository.save(drone);

        return ResponseEntity.ok(drone);
    }
}
