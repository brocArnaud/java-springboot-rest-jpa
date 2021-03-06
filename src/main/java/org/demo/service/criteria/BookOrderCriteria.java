/*
 * Created on 2017-12-20 ( Date ISO 2017-12-20 - Time 14:58:54 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package org.demo.service.criteria;

/**
 * Criteria bean used for BookOrder search.
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 */
public class BookOrderCriteria {

    private String shopCode; 
    private String customerCode; 
    private String employeeCode; 
	// Constructor
	public BookOrderCriteria() {
		// Needed empty constructor for serialization
	}

	public void setShopCode(String shopCode) {this.shopCode = shopCode;}
	public String getShopCode() {return this.shopCode;}
	public void setCustomerCode(String customerCode) {this.customerCode = customerCode;}
	public String getCustomerCode() {return this.customerCode;}
	public void setEmployeeCode(String employeeCode) {this.employeeCode = employeeCode;}
	public String getEmployeeCode() {return this.employeeCode;}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(shopCode); 
		sb.append("|"); 
		sb.append(customerCode); 
		sb.append("|"); 
		sb.append(employeeCode); 
        return sb.toString();
	}
}
