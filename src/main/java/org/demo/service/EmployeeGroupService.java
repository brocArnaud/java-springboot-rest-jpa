/*
 * Created on 2017-12-20 ( Date ISO 2017-12-20 - Time 14:58:55 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package org.demo.service;

import org.demo.entity.EmployeeGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for EmployeeGroup.
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 */
public interface EmployeeGroupService {

	/**
	 * Perform a pageable and filtered search.
	 * @param pageable pagination configuration
	 * @return a page of employeeGroup
	 */
	public Page<EmployeeGroup> search(Pageable pageable);
	/**
	 * Recover an employeeGroup following an id.
	 * @param id the given id
	 * @return the employeeGroup
	 */
	public EmployeeGroup get(String employeeCode, Short groupId);

	/**
	 * Perform an employeeGroup deletion.
	 * @param id the given id
	 * @return state of deletion (true if ok otherwise false)
	 */
	public Boolean delete(String employeeCode, Short groupId);

	/**
	 * Perform an employeeGroup creation.
	 * @param employeeGroup to create
	 * @return created employeeGroup
	 */
	public EmployeeGroup create(EmployeeGroup employeeGroup);

	/**
	 * Perform an employeeGroup update.
	 * @param employeeGroup to update
	 * @return state of update (true if ok otherwise false)
	 */
	public Boolean save(EmployeeGroup employeeGroup);

	/**
	 * Test employeeGroup existence.
	 * @param employeeGroup to check
	 * @return true if author exist otherwise false
	 */
	public Boolean exist(EmployeeGroup employeeGroup);
}