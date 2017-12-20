/*
 * Created on 2017-12-20 ( Date ISO 2017-12-20 - Time 14:58:55 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package org.demo.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Persistent class for Employee entity stored in table EMPLOYEE.
 * This class is a "record entity" without JPA links.
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 */
@Entity
@Table(name="EMPLOYEE", schema="ROOT" )
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="CODE", nullable=false, length=4)
    private String code ; 
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="SHOP_CODE", nullable=false, length=3)
    private String shopCode ; // Foreign Key     @Column(name="FIRST_NAME", length=40)
    private String firstName ;     @Column(name="LAST_NAME", nullable=false, length=40)
    private String lastName ;     @Column(name="MANAGER")
    private Boolean manager ;     @Column(name="BADGE_NUMBER")
    private Integer badgeNumber ; // Foreign Key     @Column(name="EMAIL", length=60)
    private String email ; 

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Employee() {
		super();
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
	public void setCode(String code) {
        this.code = code ;
    }
	public String getCode() {
        return this.code;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
	//--- DATABASE MAPPING : SHOP_CODE (VARCHAR) 
	public void setShopCode(String shopCode) {this.shopCode = shopCode;}
	public String getShopCode() {return this.shopCode;}

	//--- DATABASE MAPPING : FIRST_NAME (VARCHAR) 
	public void setFirstName(String firstName) {this.firstName = firstName;}
	public String getFirstName() {return this.firstName;}

	//--- DATABASE MAPPING : LAST_NAME (VARCHAR) 
	public void setLastName(String lastName) {this.lastName = lastName;}
	public String getLastName() {return this.lastName;}

	//--- DATABASE MAPPING : MANAGER (SMALLINT) 
	public void setManager(Boolean manager) {this.manager = manager;}
	public Boolean getManager() {return this.manager;}

	//--- DATABASE MAPPING : BADGE_NUMBER (INTEGER) 
	public void setBadgeNumber(Integer badgeNumber) {this.badgeNumber = badgeNumber;}
	public Integer getBadgeNumber() {return this.badgeNumber;}

	//--- DATABASE MAPPING : EMAIL (VARCHAR) 
	public void setEmail(String email) {this.email = email;}
	public String getEmail() {return this.email;}


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(code); 
		sb.append(shopCode); 
		sb.append("|"); 
		sb.append(firstName); 
		sb.append("|"); 
		sb.append(lastName); 
		sb.append("|"); 
		sb.append(manager); 
		sb.append("|"); 
		sb.append(badgeNumber); 
		sb.append("|"); 
		sb.append(email); 
        return sb.toString();
    }
}