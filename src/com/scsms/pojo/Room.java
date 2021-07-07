package com.scsms.pojo;

import java.io.Serializable;

public class Room implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String roomName;
	private String location; 
	
	public Room() {
		super();
	}
	public Room(int id, String roomName, String location) {
		super();
		this.id = id;
		this.roomName = roomName;
		this.location = location;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "Graderoom [id=" + id + ", roomName=" + roomName + ", location=" + location + "]";
	}
	
	

}
