package com.scsms.pojo;

import java.io.Serializable;

public class Mark implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String fenshu;
	private Student student;
	private String notes;
	private String state;
	private Grade grade;
	
	
	public Mark() {
		super();
	}
	

	
	public Mark(int id, String fenshu, Student student, String notes, String state, Grade grade) {
		super();
		this.id = id;
		this.fenshu = fenshu;
		this.student = student;
		this.notes = notes;
		this.state = state;
		this.grade = grade;
	}
	


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}






	public String getFenshu() {
		return fenshu;
	}



	public void setFenshu(String fenshu) {
		this.fenshu = fenshu;
	}



	public Student getStudent() {
		return student;
	}



	public void setStudent(Student student) {
		this.student = student;
	}



	public String getNotes() {
		return notes;
	}



	public void setNotes(String notes) {
		this.notes = notes;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public Grade getGrade() {
		return grade;
	}



	public void setGrade(Grade grade) {
		this.grade = grade;
	}



	@Override
	public String toString() {
		return "Mark [id=" + id + ", fenshu=" + fenshu + ", student=" + student + ", notes=" + notes + ", state=" + state
				+ ", grade=" + grade + "]";
	}
	
	
	
	

}
