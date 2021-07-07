package com.scsms.pojo;

import java.io.Serializable;

public class Notice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String notes;
	private Grade grade;
	private String time;
	
	
	public Notice() {
		super();
	}
	
	
	public Notice(int id, String notes, Grade grade, String time) {
		super();
		this.id = id;
		this.notes = notes;
		this.grade = grade;
		this.time = time;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	
	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	@Override
	public String toString() {
		return "Notice [id=" + id + ", notes=" + notes + ", grade=" + grade + ", time=" + time + "]";
	}


	
	
	
	
	

}
