package com.example.schooltrip;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.schooltrip.MODEL.Person;
import com.example.schooltrip.MODEL.Role;
import com.example.schooltrip.MODEL.Trip;
import com.example.schooltrip.REPOSITORY.PersonRepository;
import com.example.schooltrip.REPOSITORY.TripRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//@SpringBootApplication(scanBasePackages = {"com.CONTROLLER", "com.SERVICE", "com.MODEL", "com.REPOSITORY"})
//@EnableJpaRepositories("com.REPOSITORY")

//@EnableJpaRepositories(basePackages = "com.REPOSITORY")
//@EntityScan(basePackages = "com.MODEL")
public class SchooltripApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchooltripApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(PersonRepository personRepo, TripRepository tripRepo) {
		return (args) -> {
			Person p = new Person(1, "vero", "Verdi", "mrossi", "1234", Role.ADMIN);
			Person x = new Person(3, "vero", "Verdi", "mrossi", "1234", Role.STUDENT);
			personRepo.save(p);
			personRepo.save(x);
			Trip tr = new Trip(1,"name","descr", "posto", "data",50,new ArrayList<Person>(),new ArrayList<Person>());
			tripRepo.save(tr);
		
			System.out.println("Inserito: " + p);
		};
	}

}