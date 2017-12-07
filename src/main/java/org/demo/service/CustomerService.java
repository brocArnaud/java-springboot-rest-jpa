package org.demo.service;

import java.util.Optional;

import org.demo.controller.criteria.CustomerCriteria;
import org.demo.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

	public void init();

	public Page<Customer> search(Pageable pageable, CustomerCriteria criteria);

	public Optional<Customer> get(Long id);

	public Boolean delete(Long id);

	public Customer create(Customer customer);

	public Boolean save(Customer customer);

	public Boolean exist(Customer customer);
}
