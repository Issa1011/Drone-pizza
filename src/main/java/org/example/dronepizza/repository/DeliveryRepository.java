package org.example.dronepizza.repository;

import org.example.dronepizza.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
