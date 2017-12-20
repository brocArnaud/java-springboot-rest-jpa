/*
 * Created on 2017-12-20 ( Date ISO 2017-12-20 - Time 14:58:56 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package org.demo.service.criteria;

/**
 * Criteria bean used for Shop search.
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 */
public class ShopCriteria {

    private String name; 
    private String address1; 
    private String address2; 
    private String city; 
    private String countryCode; 
    private String phone; 
    private String email; 
    private String executive; 
	// Constructor
	public ShopCriteria() {
		// Needed empty constructor for serialization
	}

	public void setName(String name) {this.name = name;}
	public String getName() {return this.name;}
	public void setAddress1(String address1) {this.address1 = address1;}
	public String getAddress1() {return this.address1;}
	public void setAddress2(String address2) {this.address2 = address2;}
	public String getAddress2() {return this.address2;}
	public void setCity(String city) {this.city = city;}
	public String getCity() {return this.city;}
	public void setCountryCode(String countryCode) {this.countryCode = countryCode;}
	public String getCountryCode() {return this.countryCode;}
	public void setPhone(String phone) {this.phone = phone;}
	public String getPhone() {return this.phone;}
	public void setEmail(String email) {this.email = email;}
	public String getEmail() {return this.email;}
	public void setExecutive(String executive) {this.executive = executive;}
	public String getExecutive() {return this.executive;}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(name); 
		sb.append("|"); 
		sb.append(address1); 
		sb.append("|"); 
		sb.append(address2); 
		sb.append("|"); 
		sb.append(city); 
		sb.append("|"); 
		sb.append(countryCode); 
		sb.append("|"); 
		sb.append(phone); 
		sb.append("|"); 
		sb.append(email); 
		sb.append("|"); 
		sb.append(executive); 
        return sb.toString();
	}
}
