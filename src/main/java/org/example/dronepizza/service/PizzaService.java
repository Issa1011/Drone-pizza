package org.example.dronepizza.service;

import org.example.dronepizza.model.Pizza;
import org.example.dronepizza.repository.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> findAllPizza(){
        return pizzaRepository.findAll();
    }

    public Pizza findById(Long id){
        return pizzaRepository.findById(id).orElse(null);
    }
}
