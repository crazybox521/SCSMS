package com.scsms.pojo;

import java.io.Serializable;

public class Teacher implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String phone;
	private String email;
	private String notes;
	private String sex;
	private User user;
	private Lesson lesson;
	
	public Teacher() {
		super();
	}

	public Teacher(int id, String name, String phone, String email, String notes, String sex, User user,
			Lesson lesson) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.notes = notes;
		this.sex = sex;
		this.user = user;
		this.lesson = lesson;
	}







	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}
	



	public Lesson getLesson() {
		return lesson;
	}







	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}







	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", notes=" + notes
				+ ", sex=" + sex + ", user=" + user + ", lesson=" + lesson + "]";
	}







	



	



	
	
}
