package dev.lauchy.skyplanner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.lauchy.skyplanner.model.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByUserId(Long userId);
}
