package com.example.schooltrip.CONTROLLER;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.schooltrip.MODEL.Person;
import com.example.schooltrip.MODEL.Role;
import com.example.schooltrip.MODEL.Trip;
import com.example.schooltrip.REPOSITORY.PersonRepository;
import com.example.schooltrip.REPOSITORY.TripRepository;
import com.example.schooltrip.modelDTO.RelationDTO;
import com.example.schooltrip.modelDTO.TripDTO;

@RestController
public class TeacherController {
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

	    if (tripOpt.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trip not found");
	    }
	    
	    if (personOpt.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found");
	    }
	    
	    Trip trip = tripOpt.get();
	    Person person = personOpt.get();

	    if (person.getRole() != Role.TEACHER) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only teacher can supervise trips");
	    }

	    if (trip.getInsegnanti().contains(person)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Teacher already supervising the trip");
	    }

	    trip.getInsegnanti().add(person);
	    tripRepository.save(trip);

	    return ResponseEntity.ok("Teacher became a supervisor");
	}
	
	@PostMapping("/api/teacher/quit_supervise")
	public ResponseEntity<String> quit_supervisingTrip(@RequestBody RelationDTO relationDTO) {
		int tripId = relationDTO.gettID();
	    int personId = relationDTO.getpID();

	    Optional<Trip> optionalTrip = tripRepository.findById(tripId);
	    Optional<Person> optionalPerson = personRepository.findById(personId);

	    if (optionalTrip.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trip not found");
	    }

	    if (optionalPerson.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found");
	    }

	    Trip trip = optionalTrip.get();
	    Person person = optionalPerson.get();

	    if (trip.getInsegnanti().contains(person)) {
	        trip.getInsegnanti().remove(person);
	        tripRepository.save(trip);
	        return ResponseEntity.ok("Teacher not supervising the trip anymore");
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The teacher was not supervising the trip in the first place");
	    }
	}
	
	@GetMapping("/api/teacher/show_all_trips")//da modificare!!!!
	public ResponseEntity<List<TripDTO>> show_all_trips(@RequestBody int  teacher_id) {
		
		Optional<Person> personOpt = personRepository.findById(teacher_id);

	    if (personOpt.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }

	    Person teacher = personOpt.get();

	    if (teacher.getRole() != Role.TEACHER) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    }
	    
	    //cerca e filtra le gite
	    List<Trip> allTrips = tripRepository.findAll();
	    List<Trip> teacherTrips = allTrips.stream()
	        .filter(trip -> trip.getInsegnanti().contains(teacher))
	        .collect(Collectors.toList());

	    // Mappa da Trip a TripDTO
	    List<TripDTO> tripDTOs = teacherTrips.stream()
	        .map(trip -> new TripDTO(
	            trip.gettID(),
	            trip.getName(),
	            trip.getDescription(),
	            trip.getLocation(),
	            trip.getDate(),
	            trip.getCost(),
	            trip.getMax_partecipant()
	        ))
	        .collect(Collectors.toList());

	    return ResponseEntity.ok(tripDTOs);
	}

}
