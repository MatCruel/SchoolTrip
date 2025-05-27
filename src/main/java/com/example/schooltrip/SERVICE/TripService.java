package com.example.schooltrip.SERVICE;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.schooltrip.REPOSITORY.TripRepository;
import com.example.schooltrip.modelDTO.TripDTO;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public List<TripDTO> getAllTrips() {
        return tripRepository.findAll().stream()
        		.map(trip -> new TripDTO(
        		        trip.gettID(),
        		        trip.getName(),
        		        trip.getDescription(),
        		        trip.getLocation(),
        		        trip.getDate(),
        		        trip.getCost(),
        		        trip.getMax_partecipant()
        		)
                )
                .collect(Collectors.toList());
    }
}
