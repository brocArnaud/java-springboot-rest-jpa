/*
 * Created on 2017-12-20 ( Date ISO 2017-12-20 - Time 14:58:56 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package org.demo.repository;

import org.demo.entity.Workgroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Jpa repository for Workgroup.
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 */
@Repository
public interface WorkgroupRepository  extends JpaRepository<Workgroup, Short>, JpaSpecificationExecutor<Workgroup> {}