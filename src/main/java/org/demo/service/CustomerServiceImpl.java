package org.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.demo.controller.criteria.CustomerCriteria;
import org.demo.entity.Customer;
import org.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void init() {
		customerRepository.save(new Customer("AAA", "lil1"));
		customerRepository.save(new Customer("aabb", "lil2"));
		customerRepository.save(new Customer("ccD", "lil3"));
		customerRepository.save(new Customer("ccD", "lil4"));
		customerRepository.save(new Customer("ccD", "lil5"));
		customerRepository.save(new Customer("ccD", "lil6"));
		customerRepository.save(new Customer("ccD", "lil7"));
		customerRepository.save(new Customer("ccD", "lil8"));
		customerRepository.save(new Customer("ccD", "lil9"));
		customerRepository.save(new Customer("ccD", "lil10"));
		customerRepository.save(new Customer("ccD", "lil11"));
		customerRepository.save(new Customer("ccD", "lil12"));
	}

	@Override
	public Page<Customer> search(Pageable pageable, CustomerCriteria criteria) {
		return customerRepository.findAll(new CustomerSpecification(criteria), pageable);
	}

	@Override
	public Optional<Customer> get(Long id) {
		return customerRepository.findById(id);
	}

	@Override
	public Boolean delete(Long id) {
		if (customerRepository.findById(id) != null) {
			customerRepository.deleteById(id);
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
		final Long pk = customer.getId();
		if (customerRepository.findById(pk) != null) {
			customerRepository.save(customer);
			return true;
		}
		return false;
	}

	@Override
	public Boolean exist(Customer customer) {
		return customerRepository.existsById(customer.getId());
	}

	class CustomerSpecification implements Specification<Customer> {

		private static final long serialVersionUID = 7300336384232540051L;

		private CustomerCriteria criteria;

		public CustomerSpecification(CustomerCriteria criteria) {
			this.criteria = criteria;
		}

		@Override
		public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
			final List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotBlank(criteria.getFirstName()))
				predicates.add(builder.like(builder.lower(root.get("firstName")),
						criteria.getFirstName().toLowerCase() + "%"));
			if (StringUtils.isNotBlank(criteria.getLastName()))
				predicates.add(
						builder.like(builder.lower(root.get("lastName")), criteria.getLastName().toLowerCase() + "%"));
			return andTogether(predicates, builder);
		}

		private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder builder) {
			return builder.and(predicates.toArray(new Predicate[0]));
		}
	}

}
