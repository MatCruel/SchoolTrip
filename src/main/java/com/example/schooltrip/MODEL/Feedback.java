package com.example.schooltrip.MODEL;

import jakarta.persistence.*;


@Entity
@Table(
	    name = "feedback",
	    uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "trip_id"})
	)
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //autognerate a value
    private int fID;

    @Enumerated(EnumType.STRING)
    private Star n_stars;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Person student;

    public Feedback() {}


    public Feedback(int fID, Star n_stars, String comment, Trip trip, Person student) {
		super();
		this.fID = fID;
		this.n_stars = n_stars;
		this.comment = comment;
		this.trip = trip;
		this.student = student;
	}

	public int getfID() {
        return fID;
    }

    public void setfID(int fID) {
        this.fID = fID;
    }

    public Star getN_stars() {
        return n_stars;
    }

    public void setN_stars(Star n_stars) {
        this.n_stars = n_stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Person getStudent() {
        return student;
    }

    public void setStudent(Person student) {
        this.student = student;
    }

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}
    
}
