package org.example.dronepizza.service;


import org.example.dronepizza.repository.DeliveryRepository;
import org.example.dronepizza.repository.PizzaRepository;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final PizzaRepository pizzaRepository;



    public DeliveryService (DeliveryRepository deliveryRepository ,
                            PizzaRepository pizzaRepository){
        this.deliveryRepository = deliveryRepository;
        this.pizzaRepository = pizzaRepository;
    }


}
