package org.demo.controller;

import java.util.Optional;

import org.demo.controller.assembler.CustomerResourceAssembler;
import org.demo.controller.criteria.CustomerCriteria;
import org.demo.controller.resource.CustomerResource;
import org.demo.entity.Customer;
import org.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ExposesResourceFor(Customer.class)
@RequestMapping(value = "/customers", produces = "application/hal+json")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private PagedResourcesAssembler<Customer> pageAssember;

	private CustomerResourceAssembler customerAssembler = new CustomerResourceAssembler();

	@GetMapping("/init")
	public void init() {
		customerService.init();
	}

	@GetMapping
	public ResponseEntity<PagedResources<CustomerResource>> search(Pageable pageable, CustomerCriteria criteria) {
		final Page<Customer> result = customerService.search(pageable, criteria);
		return new ResponseEntity<PagedResources<CustomerResource>>(pageAssember.toResource(result, customerAssembler),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerResource> get(@PathVariable("id") Long id) {
		final Optional<Customer> customer = customerService.get(id);
		if (!customer.isPresent()) {
			return new ResponseEntity<CustomerResource>((CustomerResource) null, HttpStatus.NOT_FOUND);
		}
		final CustomerResource customerResource = customerAssembler.toResource(customer.get());
		return new ResponseEntity<CustomerResource>(customerResource, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<CustomerResource> create(@RequestBody Customer customer) {
		if (customerService.exist(customer))
			return new ResponseEntity<CustomerResource>((CustomerResource) null, HttpStatus.CONFLICT);
		else
			return new ResponseEntity<CustomerResource>(customerAssembler.toResource(customerService.create(customer)),
					HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody Customer customer) {
		// force the id (use the id provided by the URL)
		customer.setId(id);
		if (customerService.save(customer))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		if (customerService.delete(id))
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

}
