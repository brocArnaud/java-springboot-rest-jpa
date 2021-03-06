/*
 * Created on 2017-12-20 ( Date ISO 2017-12-20 - Time 14:58:54 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package org.demo.service.criteria;

/**
 * Criteria bean used for Book search.
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 */
public class BookCriteria {

    private String isbn; 
    private String title; 
	// Constructor
	public BookCriteria() {
		// Needed empty constructor for serialization
	}

	public void setIsbn(String isbn) {this.isbn = isbn;}
	public String getIsbn() {return this.isbn;}
	public void setTitle(String title) {this.title = title;}
	public String getTitle() {return this.title;}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(isbn); 
		sb.append("|"); 
		sb.append(title); 
        return sb.toString();
	}
}
