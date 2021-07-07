package com.scsms.pojo;

import java.io.Serializable;

public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int number;
	private Student student;
	private Grade grade;
	private String payid;
	private String alipay;
	private String state;
	
	public Payment() {
		super();
	}

	

	public Payment(int id, int number, Student student, Grade grade, String payid, String alipay, String state) {
		super();
		this.id = id;
		this.number = number;
		this.student = student;
		this.grade = grade;
		this.payid = payid;
		this.alipay = alipay;
		this.state = state;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}

	public String getAlipay() {
		return alipay;
	}

	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}
	

	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	@Override
	public String toString() {
		return "Payment [id=" + id + ", number=" + number + ", student=" + student + ", grade=" + grade + ", payid="
				+ payid + ", alipay=" + alipay + ", state=" + state + "]";
	}



	
	
	
}
