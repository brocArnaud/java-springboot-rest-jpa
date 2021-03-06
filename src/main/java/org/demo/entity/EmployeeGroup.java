/*
 * Created on 2017-12-20 ( Date ISO 2017-12-20 - Time 14:58:55 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package org.demo.entity;


import java.io.Serializable;
import javax.persistence.*;

/**
 * Persistent class for EmployeeGroup entity stored in table EMPLOYEE_GROUP.
 * This class is a "record entity" without JPA links.
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 */
@Entity
@Table(name="EMPLOYEE_GROUP", schema="ROOT" )
public class EmployeeGroup implements Serializable {

	private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( EMBEDDED IN AN EXTERNAL CLASS )  
    //----------------------------------------------------------------------
	@EmbeddedId
    private EmployeeGroupKey compositePrimaryKey ;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EmployeeGroup() {
		super();
		this.compositePrimaryKey = new EmployeeGroupKey();       
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
	public void setEmployeeCode(String employeeCode) {
        this.compositePrimaryKey.setEmployeeCode(employeeCode) ;
    }
	public String getEmployeeCode() {
        return this.compositePrimaryKey.getEmployeeCode() ;
    }
	public void setGroupId(Short groupId) {
        this.compositePrimaryKey.setGroupId(groupId) ;
    }
	public Short getGroupId() {
        return this.compositePrimaryKey.getGroupId() ;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append("[" + compositePrimaryKey + "]"); 
        return sb.toString();
    }
}