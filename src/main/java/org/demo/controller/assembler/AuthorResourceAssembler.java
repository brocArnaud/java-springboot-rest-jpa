package org.demo.controller.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.demo.controller.AuthorController;
import org.demo.controller.resource.AuthorResource;
import org.demo.entity.Author;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class AuthorResourceAssembler extends ResourceAssemblerSupport<Author, AuthorResource> {

	public AuthorResourceAssembler() {
		super(AuthorController.class, AuthorResource.class);
	}

	@Override
	public AuthorResource toResource(Author entity) {
		// FIXME improve mapping
		final AuthorResource resource = new AuthorResource(entity.getFirstName(), entity.getLastName());
		resource.setId(entity.getId());
		resource.add(linkTo(AuthorController.class).slash(entity.getId()).withSelfRel());
		return resource;
	}

}
