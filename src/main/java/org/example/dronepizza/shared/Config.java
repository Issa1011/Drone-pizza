package org.example.dronepizza.shared;

import org.example.dronepizza.model.Drone;
import org.example.dronepizza.model.Pizza;
import org.example.dronepizza.model.Station;
import org.example.dronepizza.model.Status;
import org.example.dronepizza.repository.DroneRepository;
import org.example.dronepizza.repository.PizzaRepository;
import org.example.dronepizza.repository.StationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Config implements CommandLineRunner {
    private final PizzaRepository pizzaRepository;
    private final StationRepository stationRepository;
    private final DroneRepository droneRepository;

    public Config(PizzaRepository pizzaRepository, StationRepository stationRepository, DroneRepository droneRepository){
        this.pizzaRepository = pizzaRepository;
        this.stationRepository = stationRepository;
        this.droneRepository = droneRepository;
    }

    @Override
    public void run (String ... args){
        Pizza pizza = new Pizza();
        pizza.setTitle("Ananas Pizza");
        pizza.setPrice(115);
        pizzaRepository.save(pizza);

        Pizza pizza2 = new Pizza();
        pizza2.setTitle("Salat Pizza");
        pizza2.setPrice(90);
        pizzaRepository.save(pizza2);

        Pizza pizza3 = new Pizza();
        pizza3.setTitle("Kebab Pizza");
        pizza3.setPrice(110);
        pizzaRepository.save(pizza3);

        Pizza pizza4 = new Pizza();
        pizza4.setTitle("Skinke Pizza");
        pizza4.setPrice(100);
        pizzaRepository.save(pizza4);

        Pizza pizza5 = new Pizza();
        pizza5.setTitle("Kylling Pizza");
        pizza5.setPrice(115);
        pizzaRepository.save(pizza5);


        Station station1 = new Station();
        station1.setLatitude(55.41);
        station1.setLongitude(12.34);
        stationRepository.save(station1);



        Station station2 = new Station();
        station2.setLatitude(59.41);
        station2.setLongitude(17.34);
        stationRepository.save(station2);



        Station station3 = new Station();
        station3.setLatitude(65.41);
        station3.setLongitude(21.34);
        stationRepository.save(station3);


        Drone drone1 = new Drone();
        drone1.generateUUID();
        drone1.setStatus(Status.I_DRIFT);
        drone1.setStation(station1);
        droneRepository.save(drone1);

        Drone drone2 = new Drone();
        drone2.generateUUID();
        drone2.setStatus(Status.UDFASET);
        drone2.setStation(station2);
        droneRepository.save(drone2);

        Drone drone3 = new Drone();
        drone3.generateUUID();
        drone3.setStatus(Status.UD_AF_DRIFT);
        drone3.setStation(station3);
        droneRepository.save(drone3);


    }

}
