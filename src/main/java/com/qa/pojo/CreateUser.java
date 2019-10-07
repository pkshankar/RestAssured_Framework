package com.qa.pojo;

public class CreateUser {

	private String first_name;
	private String last_name;
	private String gender;
	private String email;
	private String status;

	public CreateUser(String first_name, String last_name, String gender, String email, String status) {

		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.email = email;
		this.status = status;
	}

	public CreateUser() {

	}

	
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}

	public String getStatus() {
		return status;
	}

}
