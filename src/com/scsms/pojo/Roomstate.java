package com.scsms.pojo;

import java.io.Serializable;

public class Roomstate implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Room room;
	private String starttime;
	private String endtime;
	
	
	public Roomstate() {
		super();
	}

	


	public Roomstate(int id, Room room, String starttime, String endtime) {
		super();
		this.id = id;
		this.room = room;
		this.starttime = starttime;
		this.endtime = endtime;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	


	public Room getRoom() {
		return room;
	}




	public void setRoom(Room room) {
		this.room = room;
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




	@Override
	public String toString() {
		return "Roomstate [id=" + id + ", room=" + room + ", starttime=" + starttime + ", endtime=" + endtime + "]";
	}

	
	
	
	
	

}
