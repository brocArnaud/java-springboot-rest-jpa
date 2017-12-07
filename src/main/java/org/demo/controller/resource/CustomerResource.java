package org.demo.controller.resource;

import org.springframework.hateoas.ResourceSupport;

public class CustomerResource extends ResourceSupport {

	private Long id;

	private String firstName;

	private String lastName;

	public CustomerResource() {
	}

	public CustomerResource(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setId(Long id) {
		this.id = id;
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

}
