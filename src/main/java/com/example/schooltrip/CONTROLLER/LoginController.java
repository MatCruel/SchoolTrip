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
@CrossOrigin(origins = "*")
public class LoginController {

   @Autowired
   private PersonRepository personRepository; // o un UserRepository

   @PostMapping("/api/login/login")
   public ResponseEntity<String> login(@RequestBody LoginDataDTO loginRequest) {
       String username = loginRequest.getUsername();
       String password = loginRequest.getPsw();

       // Cerca l'utente per username
       Optional<Person> optionalUser = personRepository.findByUsername(username);

       if (optionalUser.isPresent()) {
           Person user = optionalUser.get();
           if (user.getPassword().equals(password)) { // ⚠️ Solo esempio! Meglio usare password criptate.
               return ResponseEntity.ok("Login OK!");
           } else {
               return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password sbagliata");
           }
       } else {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non trovato");
       }
   }
   }