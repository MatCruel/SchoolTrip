package com.example.schooltrip.CONTROLLER;

import com.example.schooltrip.MODEL.Person;
import com.example.schooltrip.REPOSITORY.PersonRepository;
import com.example.schooltrip.modelDTO.PersonDTO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
	//@CrossOrigin(origins = "*")
public class AdminController {
	@Autowired
    private PersonRepository personRepository;
	//@Autowired
    //private TripRepository tripRepository;
	
	@PostMapping("/api/admin/addperson")
    public ResponseEntity<String> addPerson(@RequestBody PersonDTO personDTO) {
        
        if (personRepository.findByUsername(personDTO.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                 .body("The username \"" + personDTO.getUsername() + "\" is already in use");
        }

        Person newPerson = new Person();
        newPerson.setName(personDTO.getName());
        newPerson.setSurname(personDTO.getSurname());
        newPerson.setUsername(personDTO.getUsername());
        newPerson.setPassword(personDTO.getPassword()); // In produzione: cifrare la password!
        newPerson.setRole(personDTO.getRole());

        personRepository.save(newPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body("Person added succesfully");
    }
	
	 @DeleteMapping("/api/admin/deleteperson")
	    public ResponseEntity<String> deletePerson(@RequestBody int pID) {
	        Optional<Person> person = personRepository.findById(pID);
	        if (person.isPresent()) {
	            personRepository.deleteById(pID);
	            return ResponseEntity.ok("Person deleted successfully");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found");
	        }
	    }
	
}
