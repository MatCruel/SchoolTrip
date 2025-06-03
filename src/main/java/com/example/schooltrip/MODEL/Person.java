package com.example.schooltrip.MODEL;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {
	@Id
	private int pID;
	private String name;
	private String surname;
	
	@Column(unique = true)
	private String username;
	private String password;
	private Role role; 
	
	public Person() {}
	
	public Person(int pID, String name, String surname, String username, String password, Role role) {
		super();
		this.pID = pID;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public int getpID() {
		return pID;
	}

	public void setpID(int pID) {
		this.pID = pID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Person [pID=" + pID + ", name=" + name + ", surname=" + surname + ", username=" + username
				+ ", password=" + password + ", role=" + role + "]";
	}	
}