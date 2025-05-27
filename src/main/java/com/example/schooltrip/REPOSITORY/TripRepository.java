package com.example.schooltrip.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.schooltrip.MODEL.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

	List<Trip> findByName(String s);
    // puoi anche aggiungere metodi custom come: findByUsername(String username);
	
}