package org.demo.controller.criteria;

public class AuthorCriteria {

	private String firstName;

	private String lastName;

	public AuthorCriteria() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "CustomerCriteria [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
