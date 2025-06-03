package com.example.schooltrip.modelDTO;

public class RelationDTO {
	
	private int tID;
	private int pID;
	
	public RelationDTO() {	}
	
	public RelationDTO(int tID, int pID) {
		super();
		this.tID = tID;
		this.pID = pID;
	}
	public int gettID() {
		return tID;
	}
	public void settID(int tID) {
		this.tID = tID;
	}
	public int getpID() {
		return pID;
	}
	public void setpID(int pID) {
		this.pID = pID;
	}
}
