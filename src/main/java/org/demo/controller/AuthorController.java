package org.demo.controller;

import org.demo.controller.assembler.AuthorResourceAssembler;
import org.demo.controller.criteria.AuthorCriteria;
import org.demo.controller.resource.AuthorResource;
import org.demo.entity.Author;
import org.demo.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorController.class);

	@Autowired
	private AuthorService authorService;

	@Autowired
	private PagedResourcesAssembler<Author> pageAssember;

	private AuthorResourceAssembler customerAssembler = new AuthorResourceAssembler();

	@GetMapping
	public ResponseEntity<PagedResources<AuthorResource>> search(Pageable pageable, AuthorCriteria criteria) {
		LOGGER.info("Authors search [pageable={}|AuthorCriteria={}", pageable, criteria);
		final Page<Author> result = authorService.search(pageable, criteria);
		LOGGER.info("Found {} authors !", result.getSize());
		return new ResponseEntity<>(pageAssember.toResource(result, customerAssembler), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AuthorResource> get(@PathVariable("id") Integer id) {
		LOGGER.info("Author get with id {}", id);
		final Author author = authorService.get(id);
		if (author == null) {
			LOGGER.info("Author not found");
			return new ResponseEntity<>((AuthorResource) null, HttpStatus.NOT_FOUND);
		}
		LOGGER.info("Author found : {}", author);
		final AuthorResource customerResource = customerAssembler.toResource(author);
		return new ResponseEntity<>(customerResource, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<AuthorResource> create(@RequestBody Author author) {
		LOGGER.info("Author creation request : {}", author);
		if (authorService.exist(author)) {
			LOGGER.info("Author already exist ! : {}", author);
			return new ResponseEntity<>((AuthorResource) null, HttpStatus.CONFLICT);
		} else {
			final Author created = authorService.create(author);
			LOGGER.info("Created author {}", created);
			return new ResponseEntity<>(customerAssembler.toResource(created), HttpStatus.OK);
		}
	}

	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody Author author) {
		LOGGER.info("Author update request [id={} | author={}}", id, author);
		// force the id (use the id provided by the URL)
		author.setId(id);
		if (authorService.save(author)) {
			LOGGER.info("Author succesfully updated");
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			LOGGER.info("Author not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		LOGGER.info("Author deletion request : {}", id);
		if (authorService.delete(id)) {
			LOGGER.info("Author succesfully deleted");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			LOGGER.info("Author not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
