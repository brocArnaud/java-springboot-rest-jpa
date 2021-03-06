/*
 * Created on 2017-12-20 ( Date ISO 2017-12-20 - Time 14:58:55 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package org.demo.controller.resource;

import org.springframework.hateoas.ResourceSupport;

/**
 * Hateoas resource associated with Employee.
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 */
public class EmployeeResource extends ResourceSupport {

    private String code;  
    private String shopCode;  
    private String firstName;  
    private String lastName;  
    private Boolean manager;  
    private Integer badgeNumber;  
    private String email;  

	// Constructor
	public EmployeeResource() {
		// Needed empty constructor for serialization
	}

	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getShopCode() {
		return this.shopCode;
	}
	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Boolean getManager() {
		return this.manager;
	}
	public void setManager(Boolean manager) {
		this.manager = manager;
	}
	public Integer getBadgeNumber() {
		return this.badgeNumber;
	}
	public void setBadgeNumber(Integer badgeNumber) {
		this.badgeNumber = badgeNumber;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
