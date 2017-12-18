package org.demo.controller.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.demo.controller.AuthorController;
import org.demo.controller.mapper.AuthorMapper;
import org.demo.controller.resource.AuthorResource;
import org.demo.entity.Author;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import fr.xebia.extras.selma.Selma;

public class AuthorResourceAssembler extends ResourceAssemblerSupport<Author, AuthorResource> {

	private AuthorMapper authorMapper;

	public AuthorResourceAssembler() {
		super(AuthorController.class, AuthorResource.class);
		authorMapper = Selma.builder(AuthorMapper.class).build();
	}

	@Override
	public AuthorResource toResource(Author entity) {
		final AuthorResource resource = authorMapper.map(entity);
		resource.setId(entity.getId());
		resource.add(linkTo(AuthorController.class).slash(entity.getId()).withSelfRel());
		return resource;
	}

}
