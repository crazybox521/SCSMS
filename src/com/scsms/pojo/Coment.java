package com.scsms.pojo;

import java.io.Serializable;

public class Coment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int level;
	private String notes;
	private Student student;
	private Lesson lesson;
	
	public Coment() {
		super();
	}
	public Coment(int id, int level, String notes, Student student, Lesson lesson) {
		super();
		this.id = id;
		this.level = level;
		this.notes = notes;
		this.student = student;
		this.lesson = lesson;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Lesson getLesson() {
		return lesson;
	}
	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	
	@Override
	public String toString() {
		return "Coment [id=" + id + ", level=" + level + ", notes=" + notes + ", student=" + student + ", lesson="
				+ lesson + "]";
	}
	
	

}
