/*
 * Created on 2017-12-20 ( Date ISO 2017-12-20 - Time 14:58:55 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package org.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.demo.entity.Customer;
import org.demo.repository.CustomerRepository;
import org.demo.service.criteria.CustomerCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Service implementation for Customer.
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Page<Customer> search(Pageable pageable, CustomerCriteria criteria) {
		return customerRepository.findAll(new CustomerSpecification(criteria), pageable);
	}

	@Override
	public Customer get(String code) {
		return customerRepository.findOne(code);
	}

	@Override
	public Boolean delete(String code) {
		if (customerRepository.findOne(code) != null) {
			customerRepository.delete(code);
			return true;
		}
		return false;
	}

	@Override
	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Boolean save(Customer customer) {
		final String pk = customer.getCode();
		if (customerRepository.findOne(pk) != null) {
			customerRepository.save(customer);
			return true;
		}
		return false;
	}

	@Override
	public Boolean exist(Customer customer) {
		return customerRepository.exists(customer.getCode());
	}

	class CustomerSpecification implements Specification<Customer> {

		private CustomerCriteria criteria;

		public CustomerSpecification(CustomerCriteria criteria) {
			this.criteria = criteria;
		}

		@Override
		public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
			final List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotBlank(criteria.getCountryCode())) {
				predicates.add(builder.like(builder.lower(root.get("countryCode")),
						criteria.getCountryCode().toLowerCase() + "%"));
			}
			if (StringUtils.isNotBlank(criteria.getFirstName())) {
				predicates.add(builder.like(builder.lower(root.get("firstName")),
						criteria.getFirstName().toLowerCase() + "%"));
			}
			if (StringUtils.isNotBlank(criteria.getLastName())) {
				predicates.add(builder.like(builder.lower(root.get("lastName")),
						criteria.getLastName().toLowerCase() + "%"));
			}
			if (StringUtils.isNotBlank(criteria.getLogin())) {
				predicates.add(builder.like(builder.lower(root.get("login")),
						criteria.getLogin().toLowerCase() + "%"));
			}
			if (StringUtils.isNotBlank(criteria.getPassword())) {
				predicates.add(builder.like(builder.lower(root.get("password")),
						criteria.getPassword().toLowerCase() + "%"));
			}
			if (StringUtils.isNotBlank(criteria.getCity())) {
				predicates.add(builder.like(builder.lower(root.get("city")),
						criteria.getCity().toLowerCase() + "%"));
			}
			if (StringUtils.isNotBlank(criteria.getPhone())) {
				predicates.add(builder.like(builder.lower(root.get("phone")),
						criteria.getPhone().toLowerCase() + "%"));
			}
			return andTogether(predicates, builder);
		}

		private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder builder) {
			return builder.and(predicates.toArray(new Predicate[0]));
		}
	}

}
