/*
 * Created on 2017-12-20 ( Date ISO 2017-12-20 - Time 14:58:56 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package org.demo.controller.resource;

import org.springframework.hateoas.ResourceSupport;
import java.util.Date;

/**
 * Hateoas resource associated with Workgroup.
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 */
public class WorkgroupResource extends ResourceSupport {

	// Because id is reserved in parent class, we can't override the getter.
	@SuppressWarnings("unused")
    private Short id;  
    private String name;  
    private String description;  
    private Date creationDate;  

	// Constructor
	public WorkgroupResource() {
		// Needed empty constructor for serialization
	}

	public void setId(Short id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreationDate() {
		return this.creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
