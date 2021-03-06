/*
 * Created on 2017-12-20 ( Date ISO 2017-12-20 - Time 14:58:55 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package org.demo.controller.resource;

import org.springframework.hateoas.ResourceSupport;

/**
 * Hateoas resource associated with Country.
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 */
public class CountryResource extends ResourceSupport {

    private String code;  
    private String name;  

	// Constructor
	public CountryResource() {
		// Needed empty constructor for serialization
	}

	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
