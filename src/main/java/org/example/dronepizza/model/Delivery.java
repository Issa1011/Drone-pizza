package org.example.dronepizza.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adresse;
    private LocalDateTime expectedTime;
    private LocalDateTime actualTime;

    //jpa konstrukter
    public Delivery(){

    }

    public Delivery(Long id, String adresse, LocalDateTime expectedTime, LocalDateTime actualTime, Pizza pizza) {
        this.id = id;
        this.adresse = adresse;
        this.expectedTime = expectedTime;
        this.actualTime = actualTime;
        this.pizza = pizza;
    }

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;



    @ManyToOne
    @JoinColumn(name = "drone_id", nullable = true)
    private Drone drone;


    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public LocalDateTime getActualTime() {
        return actualTime;
    }

    public void setActualTime(LocalDateTime actualTime) {
        this.actualTime = actualTime;
    }

    public LocalDateTime getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(LocalDateTime expectedTime) {
        this.expectedTime = expectedTime;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
