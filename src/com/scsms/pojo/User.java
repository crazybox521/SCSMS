package com.scsms.pojo;

import java.io.Serializable;


public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String password;
	private String userName;
	private Role role;
	private String time;
	public User() {
		super();
	}
	


	public User(int id, String password, String userName, Role role, String time) {
		super();
		this.id = id;
		this.password = password;
		this.userName = userName;
		this.role = role;
		this.time = time;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	

	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", userName=" + userName + ", role=" + role + ", time="
				+ time + "]";
	}






	
}