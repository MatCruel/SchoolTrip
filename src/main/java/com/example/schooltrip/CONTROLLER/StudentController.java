package com.example.schooltrip.CONTROLLER;

import com.example.schooltrip.MODEL.Feedback;
import com.example.schooltrip.MODEL.Person;
import com.example.schooltrip.MODEL.Role;
import com.example.schooltrip.MODEL.Trip;
import com.example.schooltrip.REPOSITORY.FeedbackRepository;
import com.example.schooltrip.REPOSITORY.PersonRepository;
import com.example.schooltrip.REPOSITORY.TripRepository;
import com.example.schooltrip.modelDTO.FeedbackDTO;
import com.example.schooltrip.modelDTO.RelationDTO;
import com.example.schooltrip.modelDTO.TripDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	@Autowired
	   private FeedbackRepository feedbackRepository;
	
	@PostMapping("/api/student/subscribe")
	public ResponseEntity<String> subscribeToTrip(@RequestBody RelationDTO relationDTO) {
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
	    
	    if (person.getRole() != Role.STUDENT) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only students can subscribe to a trip");
	    }

	    if (trip.getPartecipanti().contains(person)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Student already partecipateing");
	    }
	    
	    if (trip.getPartecipanti().size() >= trip.getMax_partecipant()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Maximum number of participants reached");
	    }

	    trip.getPartecipanti().add(person);
	    tripRepository.save(trip);

	    return ResponseEntity.ok("Student correctly subscribed to the trip");
	}
	
	@PostMapping("/api/student/unsubscribe")
	public ResponseEntity<String> unsubscribeToTrip(@RequestBody RelationDTO relationDTO) {
		int tripId = relationDTO.gettID();
	    int personId = relationDTO.getpID();

	    Optional<Trip> optionalTrip = tripRepository.findById(tripId);
	    Optional<Person> optionalPerson = personRepository.findById(personId);

	    if (optionalTrip.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trip not found");
	    }

	    if (optionalPerson.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
	    }

	    Trip trip = optionalTrip.get();
	    Person person = optionalPerson.get();

	    if (trip.getPartecipanti().contains(person)) {
	        trip.getPartecipanti().remove(person);
	        tripRepository.save(trip);
	        return ResponseEntity.ok("Student correctly unsubscribed");
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This student was not subscribed to the trip in the first place");
	    }
	}
	
	@GetMapping("/api/student/show_all_trips")
	public ResponseEntity<List<TripDTO>> show_all_trips(@RequestBody int  student_id) {
		
		Optional<Person> personOpt = personRepository.findById(student_id);

	    if (personOpt.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }

	    Person student = personOpt.get();

	    if (student.getRole() != Role.STUDENT) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    }
	    
	    //cerca e filtra le gite
	    List<Trip> allTrips = tripRepository.findAll();
	    List<Trip> studentTrips = allTrips.stream()
	        .filter(trip -> trip.getPartecipanti().contains(student))
	        .collect(Collectors.toList());

	    // Mappa da Trip a TripDTO
	    List<TripDTO> tripDTOs = studentTrips.stream()
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
	
	@PostMapping("/api/student/leavefeedback")
	public ResponseEntity<String> leave_a_feedback(@RequestBody FeedbackDTO feedbackDTO) {
		Optional<Trip> optionalTrip = tripRepository.findById(feedbackDTO.gettId());
	    Optional<Person> optionalStudent = personRepository.findById(feedbackDTO.getStudentId());

	    if (optionalTrip.isEmpty() || optionalStudent.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trip or student not found");
	    }
	    
	    Trip trip = optionalTrip.get();

	    Person student = optionalStudent.get();
	    if (student.getRole() != Role.STUDENT) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only students can leave feedback");
	    }
	    
	    if (feedbackRepository.existsByStudentAndTrip(student, trip)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Feedback already submitted for this trip by the student");
	    }

	    Feedback feedback = new Feedback();
	    feedback.setTrip(optionalTrip.get());
	    feedback.setStudent(student);
	    feedback.setN_stars(feedbackDTO.getN_stars());
	    feedback.setComment(feedbackDTO.getComment());

	    feedbackRepository.save(feedback);

	    return ResponseEntity.ok("Feedback successfully saved");
	}
	
	@DeleteMapping("/api/student/deletefeedback")//da migliorare
	public ResponseEntity<String> deleteFeedback(@RequestBody int fID) {
	    Optional<Feedback> feedbackOpt = feedbackRepository.findById(fID);
	    if (feedbackOpt.isPresent()) {
	        feedbackRepository.deleteById(fID);
	        return ResponseEntity.ok("Feedback deleted successfully");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Feedback not found");
	    }
	}

}
