package com.example.schooltrip;
/*import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


import com.example.schooltrip.MODEL.Person;
import com.example.schooltrip.MODEL.Role;
import com.example.schooltrip.MODEL.Trip;
import com.example.schooltrip.REPOSITORY.PersonRepository;
import com.example.schooltrip.REPOSITORY.TripRepository;*/

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
	
	/*@Bean
	public CommandLineRunner demo(PersonRepository personRepo, TripRepository tripRepo) {
		return (args) -> {
			Person p = new Person(5, "vero", "Verdi", "mrossi5", "1234", Role.STUDENT);
			Person x = new Person(6, "vero", "Verdi", "mrossi6", "1234", Role.STUDENT);
			personRepo.save(p);
			personRepo.save(x);
			
			ArrayList<Person> teacher = new ArrayList<Person>();
			ArrayList<Person> student = new ArrayList<Person>();
			
			teacher.add(new Person(1, "vero", "Verdi", "mrossi1", "1234", Role.TEACHER));
			teacher.add(new Person(2, "vero", "Verdi", "mrossi2", "1234", Role.TEACHER));
			teacher.add(new Person(3, "vero", "Verdi", "mrossi3", "1234", Role.TEACHER));
			
			student.add(new Person(4, "vero", "Verdi", "mrossi4", "1234", Role.STUDENT));
			student.add(new Person(5, "vero", "Verdi", "mrossi5", "1234", Role.STUDENT));
			student.add(new Person(6, "vero", "Verdi", "mrossi6", "1234", Role.STUDENT));
			
			Trip tr = new Trip(3,"name","descr", "posto", "data",500,200,teacher,student);
			tripRepo.save(tr);
		
			System.out.println("Inserito: " + p);
			System.out.println("Cerca persona con pID = 3 " + personRepo.findBypID(3));
			System.out.println("Cerca trip con nome = name " + tripRepo.findByName("name"));
		};
	}*/

}