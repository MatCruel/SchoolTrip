package com.example.schooltrip.CONTROLLER;

import com.example.schooltrip.MODEL.Person;
import com.example.schooltrip.MODEL.Trip;
import com.example.schooltrip.REPOSITORY.PersonRepository;
import com.example.schooltrip.REPOSITORY.TripRepository;
import com.example.schooltrip.modelDTO.RelationDTO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
	//@CrossOrigin(origins = "*")
public class StudentController {

	@Autowired
    private TripRepository tripRepository;
	
	@Autowired
	   private PersonRepository personRepository;
	
	@PostMapping("/api/student/subscribe")
	public ResponseEntity<String> subscribeToTrip(@RequestBody RelationDTO relationDTO) {
		 int tripId = relationDTO.gettID();
	    int personId = relationDTO.getpID();

	    Optional<Trip> tripOpt = tripRepository.findById(tripId);
	    Optional<Person> personOpt = personRepository.findById(personId);

	    if (tripOpt.isEmpty() || personOpt.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gita o studente non trovati");
	    }

	    Trip trip = tripOpt.get();
	    Person person = personOpt.get();

	    if (trip.getPartecipanti().contains(person)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Studente già iscritto alla gita");
	    }

	    trip.getPartecipanti().add(person);
	    tripRepository.save(trip);

	    return ResponseEntity.ok("Studente iscritto con successo alla gita");
	}
	
	@PostMapping("/api/student/unsubscribe")
	public ResponseEntity<String> unsubscribeToTrip(@RequestBody RelationDTO relationDTO) {
		int tripId = relationDTO.gettID();
	    int personId = relationDTO.getpID();

	    Optional<Trip> optionalTrip = tripRepository.findById(tripId);
	    Optional<Person> optionalPerson = personRepository.findById(personId);

	    if (optionalTrip.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gita non trovata");
	    }

	    if (optionalPerson.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona non trovata");
	    }

	    Trip trip = optionalTrip.get();
	    Person person = optionalPerson.get();

	    if (trip.getPartecipanti().contains(person)) {
	        trip.getPartecipanti().remove(person);
	        tripRepository.save(trip);
	        return ResponseEntity.ok("Iscrizione rimossa con successo");
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La persona non è iscritta a questa gita");
	    }
	}

}
