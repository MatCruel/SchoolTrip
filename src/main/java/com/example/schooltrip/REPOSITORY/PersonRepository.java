package com.example.schooltrip.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.schooltrip.MODEL.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    // puoi anche aggiungere metodi custom come: findByUsername(String username);
}