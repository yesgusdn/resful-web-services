package com.in28minutes.rest.webservices.restful_web_services.person;

public class PersonV1 {
	private String firstname;
	private String lastname;
	
	public PersonV1(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "PersonV1 [firstname=" + firstname + ", lastname=" + lastname + "]";
	}
	
	
}
