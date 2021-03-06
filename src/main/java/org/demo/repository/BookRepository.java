/*
 * Created on 2017-12-20 ( Date ISO 2017-12-20 - Time 14:58:54 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package org.demo.repository;

import org.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Jpa repository for Book.
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 */
@Repository
public interface BookRepository  extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {}