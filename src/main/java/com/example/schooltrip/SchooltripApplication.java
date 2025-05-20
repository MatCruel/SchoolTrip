package com.example.schooltrip;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


import com.example.schooltrip.MODEL.Person;
import com.example.schooltrip.MODEL.Role;
import com.example.schooltrip.MODEL.Trip;
import com.example.schooltrip.REPOSITORY.PersonRepository;
import com.example.schooltrip.REPOSITORY.TripRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication()
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
			Person p = new Person(6, "vero", "Verdi", "mrossi", "1234", Role.ADMIN);
			Person x = new Person(7, "vero", "Verdi", "mrossi", "1234", Role.STUDENT);
			personRepo.save(p);
			personRepo.save(x);
			
			ArrayList<Person> teacher = new ArrayList<Person>();
			ArrayList<Person> student = new ArrayList<Person>();
			
			teacher.add(new Person(1, "vero", "Verdi", "mrossi", "1234", Role.TEACHER));
			teacher.add(new Person(2, "vero", "Verdi", "mrossi", "1234", Role.TEACHER));
			teacher.add(new Person(3, "vero", "Verdi", "mrossi", "1234", Role.TEACHER));
			
			student.add(new Person(4, "vero", "Verdi", "mrossi", "1234", Role.STUDENT));
			student.add(new Person(5, "vero", "Verdi", "mrossi", "1234", Role.STUDENT));
			student.add(new Person(6, "vero", "Verdi", "mrossi", "1234", Role.STUDENT));
			
			Trip tr = new Trip(1,"name","descr", "posto", "data",50,teacher,student);
			tripRepo.save(tr);
		
			System.out.println("Inserito: " + p);
		};
	}

}