package org.example.dronepizza;

import org.example.dronepizza.model.Pizza;
import org.example.dronepizza.repository.PizzaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Config implements CommandLineRunner {
    private final PizzaRepository pizzaRepository;

    public Config(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public void run (String ... args){
        Pizza pizza = new Pizza();
        pizza.setTitle("Ananas Pizza");
        pizza.setPrice(115);
        pizzaRepository.save(pizza);
    }

}
