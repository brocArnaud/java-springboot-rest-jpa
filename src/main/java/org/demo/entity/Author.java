/*
 * Created on 2017-12-14 ( Date ISO 2017-12-14 - Time 15:03:30 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
 */
package org.demo.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Persistent class for "Author" entity stored in table "AUTHOR" <br>
 * This class is a "record entity" without JPA links <br>
 * 
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 *
 */
@Entity
@Table(name = "AUTHOR", schema = "ROOT")
public class Author implements Serializable {

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@Column(name = "ID", nullable = false)
	private Integer id;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------
	@Column(name = "FIRST_NAME", length = 40)
	private String firstName;
	@Column(name = "LAST_NAME", length = 40)
	private String lastName;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public Author() {
		super();
	}

	public Author(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------
	// --- DATABASE MAPPING : FIRST_NAME (VARCHAR)
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	// --- DATABASE MAPPING : LAST_NAME (VARCHAR)
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return this.lastName;
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(id);
		sb.append("]:");
		sb.append(firstName);
		sb.append("|");
		sb.append(lastName);
		return sb.toString();
	}

}