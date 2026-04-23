package org.example.dronepizza.controller;


import org.example.dronepizza.model.Pizza;
import org.example.dronepizza.service.PizzaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService){
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<Pizza>> findAllPizza(){
        return ResponseEntity.ok(pizzaService.findAllPizza());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> findPizzaById(@PathVariable Long id){
        Pizza pizza = pizzaService.findById(id);

        if(pizza == null){
           return ResponseEntity.notFound().build();   //Det giver fejlbesked 404
        }

        return ResponseEntity.ok(pizza);

    }

}
