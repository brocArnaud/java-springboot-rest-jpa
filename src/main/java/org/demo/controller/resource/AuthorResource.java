package org.demo.controller.resource;

import org.springframework.hateoas.ResourceSupport;

public class AuthorResource extends ResourceSupport {

	@SuppressWarnings("unused")
	private Integer id;

	private String firstName;

	private String lastName;

	public AuthorResource() {
	}

	public AuthorResource(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setId(Integer id) {
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
