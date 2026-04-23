package org.example.dronepizza.controller;


import org.example.dronepizza.service.DeliveryService;
import org.example.dronepizza.service.PizzaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deliverys")
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final PizzaService pizzaService;



    public DeliveryController(DeliveryService deliveryService,
                              PizzaService pizzaService){
        this.deliveryService = deliveryService;
        this.pizzaService = pizzaService;
    }



}
