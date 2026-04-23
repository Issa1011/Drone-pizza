package org.example.dronepizza.controller;


import org.example.dronepizza.model.Delivery;
import org.example.dronepizza.model.Drone;
import org.example.dronepizza.service.DeliveryService;
import org.example.dronepizza.service.PizzaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final PizzaService pizzaService;


    public DeliveryController(DeliveryService deliveryService,
                              PizzaService pizzaService) {
        this.deliveryService = deliveryService;
        this.pizzaService = pizzaService;
    }

    @GetMapping()
    public ResponseEntity<List<Delivery>> findAll(){
        return ResponseEntity.ok(deliveryService.findAll());
    }

    @GetMapping("/undelivered")
    public ResponseEntity<List<Delivery>> getUndelivered() {
        return ResponseEntity.ok(deliveryService.getUndelivered());
    }

    // den opretter en bestilling og leder efter en bestemt pizza
    @PostMapping("/add")
    public ResponseEntity<Delivery> addDelivery(@RequestParam String pizzaTitle){
        return deliveryService.addDelivery(pizzaTitle);
    }

    // den finder bestilling som ikke har en drone
    @GetMapping("/queue")
    public ResponseEntity<List<Delivery>> deliveryWithoutDrone(){
        return ResponseEntity.ok(deliveryService.deliveryWithoutDrone());
    }


     // ved Pathvariable viser vi vores id nummer i url - når vi bruger requestParam bliver id= ?;
    @PutMapping("/{deliveryId}/schedule")
    public ResponseEntity<Delivery> addDroneToDelivery(@RequestParam Long droneId , @PathVariable Long deliveryId ){
        return deliveryService.addDroneToDelivery(deliveryId,droneId);

    }

    @PutMapping("/{deliveryId}/finish")
    public ResponseEntity<Delivery> delivered(@PathVariable Long deliveryId){
        return deliveryService.delivered(deliveryId);

    }





}
