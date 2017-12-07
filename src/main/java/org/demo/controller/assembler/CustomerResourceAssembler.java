package org.demo.controller.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.demo.controller.CustomerController;
import org.demo.controller.resource.CustomerResource;
import org.demo.entity.Customer;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class CustomerResourceAssembler extends ResourceAssemblerSupport<Customer, CustomerResource> {

	public CustomerResourceAssembler() {
		super(CustomerController.class, CustomerResource.class);
	}

	@Override
	public CustomerResource toResource(Customer entity) {
		// FIXME improve mapping
		final CustomerResource resource = new CustomerResource(entity.getFirstName(), entity.getLastName());
		resource.setId(entity.getId());
		resource.add(linkTo(CustomerController.class).slash(entity.getId()).withSelfRel());
		return resource;
	}

}
