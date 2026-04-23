package org.example.dronepizza.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serial_uuid;

    @PrePersist
    public void generateUUID() {
        this.serial_uuid = java.util.UUID.randomUUID().toString();
    }


    private Status status;

    @OneToMany(mappedBy = "drone")
    private List<Delivery> deliveries;


    @ManyToOne
    @JoinColumn(name = "station_id")
    @JsonBackReference
    private Station station;

    public Drone(Long id, String serial_uuid, Status status, List<Delivery> deliveries) {
        this.id = id;
        this.serial_uuid = serial_uuid;
        this.status = status;
        this.deliveries = deliveries;
    }

    public Drone() {
    }

    public String getSerial_uuid() {
        return serial_uuid;
    }

    public void setSerial_uuid(String serial_uuid) {
        this.serial_uuid = serial_uuid;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
