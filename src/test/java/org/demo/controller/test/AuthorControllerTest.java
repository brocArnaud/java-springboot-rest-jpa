package org.demo.controller.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.demo.controller.resource.AuthorResource;
import org.demo.entity.Author;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// Junit specific runner
@RunWith(SpringJUnit4ClassRunner.class)
// Declare as spring boot test and define the port according to properties in
// src/test/resources/application.properties
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
// Ensure all are clean between two method test execution
@SqlGroup({ @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:clean.sql") })
public class AuthorControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	private static final String FIRSTNAME = "arnaud";
	private static final String LASTNAME = "brochain";

	@Test
	public void createAuthorTest() {
		// Create an author
		Author author = createMocKAuthor(1, FIRSTNAME, LASTNAME);
		// Recover this author
		ResponseEntity<AuthorResource> result = this.restTemplate.getForEntity("/authors/1", AuthorResource.class);
		// The response can't be null
		assertThat(result).isNotNull();
		// The status code must be OK
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		// Response body must not be null
		assertThat(result.getBody()).isNotNull();
		// Assert the Hateoas self link
		Link link = new Link("http://localhost:9999/authors/1", Link.REL_SELF);
		assertThat(result.getBody().getId()).isEqualTo(link);
		assertThat(result.getBody().getFirstName()).isEqualTo(FIRSTNAME);
		assertThat(result.getBody().getLastName()).isEqualTo(LASTNAME);

		// Try to create the same author, must return a CONFLICT status code
		result = this.restTemplate.postForEntity("/authors", author, AuthorResource.class);
		// The reponse can't be null
		assertThat(result).isNotNull();
		// The status code must be OK
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
		// Response body must not be null
		assertThat(result.getBody()).isNull();
	}

	@Test
	public void updateTest() {
		// Create an author and assert field
		Author author = createMocKAuthor(1, FIRSTNAME, LASTNAME);
		// Recover this author
		ResponseEntity<AuthorResource> result = this.restTemplate.getForEntity("/authors/1", AuthorResource.class);
		// The reponse can't be null
		assertThat(result).isNotNull();
		// The status code must be OK
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		// Response body must not be null
		assertThat(result.getBody()).isNotNull();
		// Assert the Hateoas self link
		Link link = new Link("http://localhost:9999/authors/1", Link.REL_SELF);
		assertThat(result.getBody().getId()).isEqualTo(link);
		assertThat(result.getBody().getFirstName()).isEqualTo(FIRSTNAME);
		assertThat(result.getBody().getLastName()).isEqualTo(LASTNAME);

		// Change field value
		author.setFirstName("newFirsname");
		author.setLastName("newlastName");
		// Process update
		RequestEntity<Author> request = new RequestEntity<Author>(author, HttpMethod.PUT, null);
		ResponseEntity<Void> resultUpdate = this.restTemplate.exchange("/authors/1", HttpMethod.PUT, request,
				getTypeRefVoid());
		// The response can't be null
		assertThat(resultUpdate).isNotNull();
		// The status code must be OK
		assertThat(resultUpdate.getStatusCode()).isEqualTo(HttpStatus.OK);

		// Recover the author and ensure field are correct
		result = this.restTemplate.getForEntity("/authors/1", AuthorResource.class);
		// The reponse can't be null
		assertThat(result).isNotNull();
		// The status code must be OK
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		// Response body must not be null
		assertThat(result.getBody()).isNotNull();
		// Assert the Hateoas self link
		link = new Link("http://localhost:9999/authors/1", Link.REL_SELF);
		assertThat(result.getBody().getId()).isEqualTo(link);
		assertThat(result.getBody().getFirstName()).isEqualTo("newFirsname");
		assertThat(result.getBody().getLastName()).isEqualTo("newlastName");
	}

	@Test
	public void updateNotFoundTest() {
		// Process update
		RequestEntity<Author> request = new RequestEntity<Author>(new Author(), HttpMethod.PUT, null);
		ResponseEntity<Void> resultUpdate = this.restTemplate.exchange("/authors/99999", HttpMethod.PUT, request,
				getTypeRefVoid());
		// The response can't be null
		assertThat(resultUpdate).isNotNull();
		// The status code must be NOT_FOUND
		assertThat(resultUpdate.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	public void deleteTest() {
		// Create an author
		createMocKAuthor(1, FIRSTNAME, LASTNAME);
		// Recover this author and test if all is ok
		ResponseEntity<AuthorResource> result = this.restTemplate.getForEntity("/authors/1", AuthorResource.class);
		// The reponse can't be null
		assertThat(result).isNotNull();
		// The status code must be OK
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

		// Delete the author
		RequestEntity<Author> request = new RequestEntity<Author>(new Author(), HttpMethod.DELETE, null);
		ResponseEntity<Void> resultDelete = this.restTemplate.exchange("/authors/1", HttpMethod.DELETE, request,
				getTypeRefVoid());
		// The response can't be null
		assertThat(resultDelete).isNotNull();
		// The status code must be NOT_CONTENT
		assertThat(resultDelete.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

		// Try to recover the author and ensure it's don't exist
		// Recover this author and test if all is ok
		result = this.restTemplate.getForEntity("/authors/1", AuthorResource.class);
		// The reponse can't be null
		assertThat(result).isNotNull();
		// The status code must be NOT_FOUND
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	public void deleteNotFound() {
		// Recover this author and test if all is ok
		ResponseEntity<AuthorResource> result = this.restTemplate.getForEntity("/authors/9999", AuthorResource.class);
		// The reponse can't be null
		assertThat(result).isNotNull();
		// The status code must be NOT_FOUND
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		// Delete the author
		RequestEntity<Author> request = new RequestEntity<Author>(new Author(), HttpMethod.DELETE, null);
		ResponseEntity<Void> resultDelete = this.restTemplate.exchange("/authors/9999", HttpMethod.DELETE, request,
				getTypeRefVoid());
		// The response can't be null
		assertThat(resultDelete).isNotNull();
		// The status code must be NOT_FOUND
		assertThat(resultDelete.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	public void getAuthorsTest() {
		// First call assert no element
		ResponseEntity<PagedResources<AuthorResource>> result = this.search("/authors");
		// The reponse can't be null
		assertThat(result).isNotNull();
		// The status code must be OK
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		// Response body must not be null
		PagedResources<AuthorResource> page = result.getBody();
		assertThat(page).isNotNull();
		assertThat(page.getMetadata()).isNotNull();
		assertThat(page.getMetadata().getTotalElements()).isEqualTo(0);
		assertThat(page.getMetadata().getTotalPages()).isEqualTo(0);
		// By default page size is 20
		assertThat(page.getMetadata().getSize()).isEqualTo(20);

		// Insert 21 author
		for (int i = 1; i < 22; i++) {
			createMocKAuthor(i, FIRSTNAME + i, LASTNAME + i);
		}
		result = this.search("/authors");
		// Response body must not be null
		page = result.getBody();
		assertThat(page).isNotNull();
		assertThat(page.getMetadata().getTotalElements()).isEqualTo(21);
		assertThat(page.getMetadata().getTotalPages()).isEqualTo(2);

		// Change the number of element per page and assert the page number
		result = this.search("/authors?size=3");
		// Response body must not be null
		page = result.getBody();
		assertThat(page).isNotNull();
		assertThat(page.getMetadata().getTotalElements()).isEqualTo(21);
		assertThat(page.getMetadata().getTotalPages()).isEqualTo(7);

		// Test the filter, create two more author
		createMocKAuthor(23, "ccarnaud", LASTNAME);
		createMocKAuthor(24, FIRSTNAME, "ddbrochain");
		// Test First name filter
		result = this.search("/authors?size=3&firstName=cc");
		// Response body must not be null
		page = result.getBody();
		assertThat(page).isNotNull();
		assertThat(page.getMetadata().getTotalElements()).isEqualTo(1);
		assertThat(page.getMetadata().getTotalPages()).isEqualTo(1);
		for (AuthorResource authorResource : page.getContent()) {
			assertThat(authorResource.getFirstName()).startsWith("cc");
		}
		// Test Last name filter
		result = this.search("/authors?size=3&lastName=dd");
		// Response body must not be null
		page = result.getBody();
		assertThat(page).isNotNull();
		assertThat(page.getMetadata().getTotalElements()).isEqualTo(1);
		assertThat(page.getMetadata().getTotalPages()).isEqualTo(1);
		for (AuthorResource authorResource : page.getContent()) {
			assertThat(authorResource.getLastName()).startsWith("dd");
		}

		// Finally test all filters together : normally no result !
		result = this.search("/authors?size=3&lastName=dd&firstName=cc");
		// Response body must not be null
		page = result.getBody();
		assertThat(page).isNotNull();
		assertThat(page.getMetadata().getTotalElements()).isEqualTo(0);
		assertThat(page.getMetadata().getTotalPages()).isEqualTo(0);
	}

	private Author createMocKAuthor(Integer id, String firstName, String lastName) {
		Author author = new Author(firstName, lastName);
		author.setId(id);
		this.restTemplate.postForLocation("/authors", author);
		return author;
	}

	private ResponseEntity<PagedResources<AuthorResource>> search(String uri) {
		return this.restTemplate.exchange(uri, HttpMethod.GET, null, getTypeRef());
	}

	private ParameterizedTypeReference<PagedResources<AuthorResource>> getTypeRef() {
		return new ParameterizedTypeReference<PagedResources<AuthorResource>>() {
		};
	}

	private ParameterizedTypeReference<Void> getTypeRefVoid() {
		return new ParameterizedTypeReference<Void>() {
		};
	}

}
