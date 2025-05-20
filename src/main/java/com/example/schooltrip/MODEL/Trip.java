package com.example.schooltrip.MODEL;

import java.util.*;

import jakarta.persistence.*;


@Entity
public class Trip {
	@Id
	private int tID;
	private String name;
	private String description;
	private String location;
	private String date;
	private int cost;
	
	@ManyToMany
    @JoinTable(
        name = "trip-->teachers",
        joinColumns = @JoinColumn(name = "trip_id"),
        inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> insegnanti;

    @ManyToMany
    @JoinTable(
        name = "trip-->partecipants",
        joinColumns = @JoinColumn(name = "trip_id"),
        inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> partecipanti;
	
	
	public Trip() {}

	public Trip(int tID, String name, String description, String location, String date, int cost,
			ArrayList<Person> insegnanti, ArrayList<Person> partecipanti) {
		super();
		this.tID = tID;
		this.name = name;
		this.description = description;
		this.location = location;
		this.date = date;
		this.cost = cost;
		this.insegnanti = insegnanti;
		this.partecipanti = partecipanti;
	}

	public int gettID() {
		return tID;
	}

	public void settID(int tID) {
		this.tID = tID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public List<Person> getInsegnanti() {
		return insegnanti;
	}

	public void setInsegnanti(List<Person> insegnanti) {
		this.insegnanti = insegnanti;
	}

	public List<Person> getPartecipanti() {
		return partecipanti;
	}

	public void setPartecipanti(List<Person> partecipanti) {
		this.partecipanti = partecipanti;
	}

	@Override
	public String toString() {
		return "Trip [tID=" + tID + ", name=" + name + ", description=" + description + ", location=" + location
				+ ", date=" + date + ", cost=" + cost + ", insegnanti=" + insegnanti + ", partecipanti=" + partecipanti
				+ "]";
	}
}
