package org.example.dronepizza.service;


import org.example.dronepizza.model.Delivery;
import org.example.dronepizza.model.Drone;
import org.example.dronepizza.model.Pizza;
import org.example.dronepizza.model.Status;
import org.example.dronepizza.repository.DeliveryRepository;
import org.example.dronepizza.repository.DroneRepository;
import org.example.dronepizza.repository.PizzaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final PizzaRepository pizzaRepository;
    private final DroneRepository droneRepository;


    public DeliveryService(DeliveryRepository deliveryRepository,
                           PizzaRepository pizzaRepository, DroneRepository droneRepository) {
        this.deliveryRepository = deliveryRepository;
        this.pizzaRepository = pizzaRepository;
        this.droneRepository = droneRepository;
    }

    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    public List<Delivery> getUndelivered() {
        return deliveryRepository.findAll()
                .stream()
                .filter(d -> !d.isCompleted())
                .toList();
    }

    public ResponseEntity<Delivery> addDelivery(String pizzaTitle) {
        List<Pizza> pizzas = pizzaRepository.findAll();
        Pizza pizza = pizzas.stream()
                .filter(p -> p.getTitle().equals(pizzaTitle))  //pizzaTitle er det input som brugeren giver, som stream derefter filterer efter
                .findFirst()   // Tager den første der matcher fra databasen
                .orElse(null);


        if (pizzas == null) {
            return ResponseEntity.notFound().build();
        }


        Delivery newDelivery = new Delivery();
        newDelivery.setPizza(pizza);
        newDelivery.setAdresse("Nørregade 1");
        newDelivery.setExpectedTime(LocalDateTime.now().plusMinutes(30));   // // en halv time fra nu
        deliveryRepository.save(newDelivery);

        return ResponseEntity.status(200).body(newDelivery);

    }

    public List<Delivery> deliveryWithoutDrone() {
        return deliveryRepository.findAll()
                .stream()
                .filter(d -> d.getDrone() == null)
                .toList();
    }


    public ResponseEntity<Delivery> addDroneToDelivery(Long deliveryId, Long droneId) {
        Delivery delivery = deliveryRepository.findById(deliveryId).orElse(null);

        if (delivery == null) {
            return ResponseEntity.notFound().build();
        }

        if (delivery.getDrone() != null) {
            return ResponseEntity.badRequest().build();
        }

        Drone drone = droneRepository.findById(droneId).orElse(null);

        if (drone == null) {
            return ResponseEntity.notFound().build();
        }

        if (drone.getStatus() != Status.I_DRIFT) {
            return ResponseEntity.badRequest().build();
        }

        delivery.setDrone(drone);
        deliveryRepository.save(delivery);

        return ResponseEntity.status(200).build();


    }

    public ResponseEntity<Delivery> delivered(Long deliveryId){
        Delivery delivery = deliveryRepository.findById(deliveryId).orElse(null);

        if (delivery == null) {
            return ResponseEntity.notFound().build();
        }

        if (delivery.getDrone() == null){
            return ResponseEntity.badRequest().build();
        }

        if (delivery.isCompleted()){
            return ResponseEntity.badRequest().build();
        }

        delivery.setActualTime(LocalDateTime.now());
        deliveryRepository.save(delivery);

        return ResponseEntity.ok(delivery);

    }

}


