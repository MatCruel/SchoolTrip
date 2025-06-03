package com.example.schooltrip.CONTROLLER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.schooltrip.MODEL.Trip;
import com.example.schooltrip.REPOSITORY.TripRepository;
import com.example.schooltrip.SERVICE.TripService;
import com.example.schooltrip.modelDTO.TripDTO;

@RestController
//@CrossOrigin(origins = "*")  
public class TripController {
    @Autowired
    private TripService tripService;
    
    @Autowired
    private TripRepository tripRepository;

    @GetMapping("/api/trips/getall")
    public ResponseEntity<List<TripDTO>> getAllTrips() {
        List<TripDTO> trips = tripService.getAllTrips();
        return ResponseEntity.ok(trips);
    }
    
    @PostMapping("/api/trips/add")
	public ResponseEntity<String> addTrip(@RequestBody TripDTO tripDTO) {
    	
        if (tripRepository.existsById(tripDTO.gettID())) {
            // Se esiste, ritorna un messaggio di errore
            return ResponseEntity.status(HttpStatus.CONFLICT).body("La gita con tID " + tripDTO.gettID() + " esiste già.");
        }
	    
    	// Converte il TripDTO in un oggetto Trip (l'entità salvabile)
    	Trip newTrip = new Trip();
		    newTrip.settID(tripDTO.gettID());
		    newTrip.setName(tripDTO.getName());
		    newTrip.setDescription(tripDTO.getDescription());
		    newTrip.setLocation(tripDTO.getLocation());
		    newTrip.setDate(tripDTO.getDate());
		    newTrip.setCost(tripDTO.getCost());
		    newTrip.setMax_partecipant(tripDTO.getMax_partecipant());

	    // Salva la gita nel database
	    tripRepository.save(newTrip);

	    return ResponseEntity.ok("Gita aggiunta con successo!");
    }
    	
}
