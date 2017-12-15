package org.demo.controller;

import org.demo.controller.assembler.AuthorResourceAssembler;
import org.demo.controller.criteria.AuthorCriteria;
import org.demo.controller.resource.AuthorResource;
import org.demo.entity.Author;
import org.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
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
@RequestMapping(value = "/authors", produces = "application/hal+json")
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@Autowired
	private PagedResourcesAssembler<Author> pageAssember;

	private AuthorResourceAssembler customerAssembler = new AuthorResourceAssembler();

	@GetMapping("/init")
	public void init() {
		authorService.init();
	}

	@GetMapping
	public ResponseEntity<PagedResources<AuthorResource>> search(Pageable pageable, AuthorCriteria criteria) {
		final Page<Author> result = authorService.search(pageable, criteria);
		return new ResponseEntity<PagedResources<AuthorResource>>(pageAssember.toResource(result, customerAssembler),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AuthorResource> get(@PathVariable("id") Integer id) {
		final Author customer = authorService.get(id);
		if (customer == null) {
			return new ResponseEntity<AuthorResource>((AuthorResource) null, HttpStatus.NOT_FOUND);
		}
		final AuthorResource customerResource = customerAssembler.toResource(customer);
		return new ResponseEntity<AuthorResource>(customerResource, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<AuthorResource> create(@RequestBody Author author) {
		if (authorService.exist(author))
			return new ResponseEntity<AuthorResource>((AuthorResource) null, HttpStatus.CONFLICT);
		else
			return new ResponseEntity<AuthorResource>(customerAssembler.toResource(authorService.create(author)),
					HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody Author customer) {
		// force the id (use the id provided by the URL)
		customer.setId(id);
		if (authorService.save(customer))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		if (authorService.delete(id))
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

}
