/*
 * Created on 2017-12-20 ( Date ISO 2017-12-20 - Time 14:58:56 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package org.demo.controller.resource;

import org.springframework.hateoas.ResourceSupport;

/**
 * Hateoas resource associated with Synopsis.
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 */
public class SynopsisResource extends ResourceSupport {

    private Integer bookId;  
    private String synopsis;  

	// Constructor
	public SynopsisResource() {
		// Needed empty constructor for serialization
	}

	public Integer getBookId() {
		return this.bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getSynopsis() {
		return this.synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
}
