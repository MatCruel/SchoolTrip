package com.example.schooltrip.CONTROLLER;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.schooltrip.MODEL.Feedback;
import com.example.schooltrip.MODEL.Trip;
import com.example.schooltrip.REPOSITORY.FeedbackRepository;
import com.example.schooltrip.REPOSITORY.TripRepository;
import com.example.schooltrip.SERVICE.TripService;
import com.example.schooltrip.modelDTO.FeedbackDTO;
import com.example.schooltrip.modelDTO.TripDTO;

@RestController
//@CrossOrigin(origins = "*")  
public class TripController {
    @Autowired
    private TripService tripService;
    
    @Autowired
    private TripRepository tripRepository;
    
    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping("/api/trips/getall")
    public ResponseEntity<List<TripDTO>> getAllTrips() {
        List<TripDTO> trips = tripService.getAllTrips();
        return ResponseEntity.ok(trips);
    }
    
    @PostMapping("/api/trips/add")
	public ResponseEntity<String> addTrip(@RequestBody TripDTO tripDTO) {
	    
    	// Converte il TripDTO in un oggetto Trip (l'entità salvabile)
    	Trip newTrip = new Trip();
		    newTrip.setName(tripDTO.getName());
		    newTrip.setDescription(tripDTO.getDescription());
		    newTrip.setLocation(tripDTO.getLocation());
		    newTrip.setDate(tripDTO.getDate());
		    newTrip.setCost(tripDTO.getCost());
		    newTrip.setMax_partecipant(tripDTO.getMax_partecipant());

	    // Salva la gita nel database
	    tripRepository.save(newTrip);

	    return ResponseEntity.ok("Trip successfully added");
    }
    
    @PostMapping("/api/trips/delete")
	public ResponseEntity<String> addTrip(@RequestBody int tripID) {
    	Optional<Trip> optionalTrip = tripRepository.findById(tripID);

        if (optionalTrip.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trip not found");
        }

        tripRepository.deleteById(tripID);
        return ResponseEntity.ok("Trip successfully deleted");
    }
    
    @GetMapping("/api/trips/getfeedbacks")
	public ResponseEntity<List<FeedbackDTO>> getFeedbacks(@RequestBody int tID) {
    	 List<Feedback> feedbackList = feedbackRepository.findByTrip_tID(tID);

    	    List<FeedbackDTO> dtos = feedbackList.stream()
    	        .map(f -> new FeedbackDTO(
    	            f.getTrip().gettID(),
    	            f.getStudent().getpID(),
    	            f.getN_stars(),
    	            f.getComment()
    	        ))
    	        .toList();

    	return ResponseEntity.ok(dtos);
    }
    	
}
