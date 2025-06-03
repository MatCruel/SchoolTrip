package com.example.schooltrip.MODEL;

import java.util.List;

import jakarta.persistence.*;


@Entity
public class Trip {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tID;
	private String name;
	private String description;
	private String location;
	private String date;
	private int cost;
	private int max_partecipant;
	
	@ManyToMany//(fetch = FetchType.EAGER)//valutare se serve
    @JoinTable(
        name = "trip-->teachers",
        joinColumns = @JoinColumn(name = "trip_id"),
        inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> insegnanti;

    @ManyToMany//(fetch = FetchType.EAGER)//valutare se serve
    @JoinTable(
        name = "trip-->partecipants",
        joinColumns = @JoinColumn(name = "trip_id"),
        inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> partecipanti;
    
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;
	
	
	public Trip() {}

	public Trip(int tID, String name, String description, String location, String date, int cost, int max_partecipant,
			List<Person> insegnanti, List<Person> partecipanti, List<Feedback> feedbacks) {
		super();
		this.tID = tID;
		this.name = name;
		this.description = description;
		this.location = location;
		this.date = date;
		this.cost = cost;
		this.max_partecipant = max_partecipant;
		this.insegnanti = insegnanti;
		this.partecipanti = partecipanti;
		this.feedbacks = feedbacks;
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

	public int getMax_partecipant() {
		return max_partecipant;
	}

	public void setMax_partecipant(int max_partecipant) {
		this.max_partecipant = max_partecipant;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	@Override
	//@Transactional //valutare se serve
	public String toString() {
		return "Trip [tID=" + tID + ", name=" + name + ", description=" + description + ", location=" + location
				+ ", date=" + date + ", cost=" + cost + ", insegnanti  ..., partecipanti  ..." + "]";
	}
}
