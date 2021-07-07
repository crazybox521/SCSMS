package com.scsms.pojo;

import java.io.Serializable;

public class Grade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String gradeName;
	private Room room;
	private Lesson lesson;
	private Teacher teacher;
	private String notes;
	private String starttime;
	private String endtime;
	private String state;
	
	public Grade() {
		super();
	}







	public Grade(int id, String gradeName, Room room, Lesson lesson, Teacher teacher, String notes, String starttime,
			String endtime, String state) {
		super();
		this.id = id;
		this.gradeName = gradeName;
		this.room = room;
		this.lesson = lesson;
		this.teacher = teacher;
		this.notes = notes;
		this.starttime = starttime;
		this.endtime = endtime;
		this.state = state;
	}







	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


    
    
	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Lesson getLesson() {
		return lesson;
	}
	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	

	public String getStarttime() {
		return starttime;
	}



	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}



	public String getEndtime() {
		return endtime;
	}



	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Grade [id=" + id + ", gradeName=" + gradeName + ", room=" + room + ", lesson=" + lesson + ", teacher="
				+ teacher + ", notes=" + notes + ", starttime=" + starttime + ", endtime=" + endtime + ", state="
				+ state + "]";
	}

	
	
	

}
