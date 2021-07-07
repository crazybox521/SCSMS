package com.scsms.pojo;

import java.io.Serializable;

public class Lesson implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String lessonName;
	private String notes;
	private int price;
	private int hours;
	
	public Lesson() {
		super();
	}

	


	public Lesson(int id, String lessonName, String notes, int price, int hours) {
		super();
		this.id = id;
		this.lessonName = lessonName;
		this.notes = notes;
		this.price = price;
		this.hours = hours;
	}




	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLessonName() {
		return lessonName;
	}


	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	


	public int getHours() {
		return hours;
	}




	public void setHours(int hours) {
		this.hours = hours;
	}




	@Override
	public String toString() {
		return "Lesson [id=" + id + ", lessonName=" + lessonName + ", notes=" + notes + ", price=" + price + ", hours="
				+ hours + "]";
	}






	


}
