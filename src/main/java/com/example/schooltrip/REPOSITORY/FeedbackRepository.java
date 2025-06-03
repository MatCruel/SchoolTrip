package com.example.schooltrip.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.schooltrip.MODEL.Feedback;
import com.example.schooltrip.MODEL.Person;
import com.example.schooltrip.MODEL.Trip;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    boolean existsByStudentAndTrip(Person student, Trip trip);
    
    List<Feedback> findByTrip_tID(int tID);
}
