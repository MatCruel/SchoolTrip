package com.example.schooltrip.modelDTO;

public class TripDTO {
	
	
	private int tID;
	private String name;
	private String description;
	private String location;
	private String date;
	private int cost;
	private int max_partecipant;	
	
	public TripDTO() {}

	public TripDTO(int tID, String name, String description, String location, String date, int cost,
			int max_partecipant) {
		super();
		this.tID = tID;
		this.name = name;
		this.description = description;
		this.location = location;
		this.date = date;
		this.cost = cost;
		this.max_partecipant = max_partecipant;
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

	public int getMax_partecipant() {
		return max_partecipant;
	}

	public void setMax_partecipant(int max_partecipant) {
		this.max_partecipant = max_partecipant;
	}
}
