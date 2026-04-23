package org.example.dronepizza.service;


import org.example.dronepizza.model.Station;
import org.example.dronepizza.repository.DroneRepository;
import org.example.dronepizza.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

    private final StationRepository stationRepository;
    private final DroneRepository droneRepository;


    public StationService (StationRepository stationRepository ,
                           DroneRepository droneRepository){
        this.droneRepository = droneRepository;
        this.stationRepository = stationRepository;
    }


    public List<Station> findAll(){
        return stationRepository.findAll();
    }
}
