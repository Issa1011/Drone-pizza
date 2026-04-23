package org.example.dronepizza.repository;

import org.example.dronepizza.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
}
