package com.example.schooltrip.modelDTO;

import com.example.schooltrip.MODEL.Star;

public class FeedbackDTO {
	private int tId;
    private int studentId;
    private Star n_stars;
    private String comment;
	
    public FeedbackDTO() {}
    
    public FeedbackDTO(int tId, int studentId, Star n_stars, String comment) {
		super();
		this.tId = tId;
		this.studentId = studentId;
		this.n_stars = n_stars;
		this.comment = comment;
	}

	public int gettId() {
		return tId;
	}

	public void settId(int tId) {
		this.tId = tId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
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
}
