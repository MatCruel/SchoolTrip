package com.example.schooltrip.REPOSITORY;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.schooltrip.MODEL.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
	List<Person> findBypID(int pID);

	Optional<Person> findByUsername(String username);
}