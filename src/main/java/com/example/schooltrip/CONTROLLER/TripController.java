package com.example.schooltrip.CONTROLLER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.schooltrip.SERVICE.TripService;
import com.example.schooltrip.modelDTO.TripDTO;

@RestController
//@CrossOrigin(origins = "*")  
public class TripController {
    @Autowired
    private TripService tripService;

    @GetMapping("/api/trips/getall")
    public ResponseEntity<List<TripDTO>> getAllTrips() {
        List<TripDTO> trips = tripService.getAllTrips();
        return ResponseEntity.ok(trips);
    }
}
