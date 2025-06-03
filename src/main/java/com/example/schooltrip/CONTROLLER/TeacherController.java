package com.example.schooltrip.CONTROLLER;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.schooltrip.MODEL.Person;
import com.example.schooltrip.MODEL.Trip;
import com.example.schooltrip.REPOSITORY.PersonRepository;
import com.example.schooltrip.REPOSITORY.TripRepository;
import com.example.schooltrip.modelDTO.RelationDTO;

public class TeacherController { //da TESTARE PER INTERO
	@Autowired
    private TripRepository tripRepository;
	
	@Autowired
	   private PersonRepository personRepository;
	
	@PostMapping("/api/teacher/supervise")
	public ResponseEntity<String> superviseTrip(@RequestBody RelationDTO relationDTO) {
		int tripId = relationDTO.gettID();
	    int personId = relationDTO.getpID();

	    Optional<Trip> tripOpt = tripRepository.findById(tripId);
	    Optional<Person> personOpt = personRepository.findById(personId);

	    if (tripOpt.isEmpty() || personOpt.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gita o studente non trovati");
	    }

	    Trip trip = tripOpt.get();
	    Person person = personOpt.get();

	    if (trip.getInsegnanti().contains(person)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Studente già iscritto alla gita");
	    }

	    trip.getInsegnanti().add(person);
	    tripRepository.save(trip);

	    return ResponseEntity.ok("Studente iscritto con successo alla gita");
	}
	
	@PostMapping("/api/teacher/stop_supervising")
	public ResponseEntity<String> stop_supervisingTrip(@RequestBody RelationDTO relationDTO) {
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

	    if (trip.getInsegnanti().contains(person)) {
	        trip.getInsegnanti().remove(person);
	        tripRepository.save(trip);
	        return ResponseEntity.ok("Iscrizione rimossa con successo");
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La persona non è iscritta a questa gita");
	    }
	}

}
