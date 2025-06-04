package com.example.schooltrip.CONTROLLER;

import com.example.schooltrip.MODEL.Person;
import com.example.schooltrip.REPOSITORY.PersonRepository;
import com.example.schooltrip.modelDTO.LoginDataDTO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private PersonRepository personRepository;

    /**
     * This method now handles form‐encoded POSTs to /login,
     * reading “username” and “password” from the request parameters.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        // Cerca l'utente per username
        Optional<Person> optionalUser = personRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            Person user = optionalUser.get();
            // (In production, NEVER store plain‐text passwords.)
            if (user.getPassword().equals(password)) {
                return ResponseEntity.ok("Login OK!");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong password");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
    }

} 
