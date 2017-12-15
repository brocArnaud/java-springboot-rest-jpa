package org.demo.controller.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// Junit specific runner
@RunWith(SpringJUnit4ClassRunner.class)
// Declare as spring boot test and define the port according to properties in
// src/test/resources/application.properties
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
// Ensure all are clean between two method test execution
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class AuthorControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	private static final String FIRSTNAME = "arnaud";
	private static final String LASTNAME = "brochain";

	@Test
	public void createAuthorTest() {
		System.out.println("===============> LALALALA 22");
		// Create an author
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

		// Try to create the same author, must return a CONFLICT status code
		result = this.restTemplate.postForEntity("/authors", author, AuthorResource.class);
		// The reponse can't be null
		assertThat(result).isNotNull();
		// The status code must be OK
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
		// Response body must not be null
		assertThat(result.getBody()).isNull();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void getAuthorsTest() {
		System.out.println("===============> LALALALA");
		// First call assert no element
		ResponseEntity<PagedResources<AuthorResource>> result = this.restTemplate.exchange("/authors", HttpMethod.GET,
				null, getTypeRef());
		// ResponseEntity<PagedResources> result =
		// this.restTemplate.getForEntity("/authors", PagedResources.class);
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
		// result = this.restTemplate.getForEntity("/authors", PagedResources.class);
		result = this.restTemplate.exchange("/authors", HttpMethod.GET, null, getTypeRef());
		// Response body must not be null
		page = result.getBody();
		assertThat(page).isNotNull();
		assertThat(page.getMetadata().getTotalElements()).isEqualTo(21);
		assertThat(page.getMetadata().getTotalPages()).isEqualTo(2);

		// Change the number of element per page and assert the page number
		result = this.restTemplate.exchange("/authors?size=3", HttpMethod.GET, null, getTypeRef());
//		result = this.restTemplate.getForEntity("/authors?size=3", PagedResources.class);
		// Response body must not be null
		page = result.getBody();
		assertThat(page).isNotNull();
		assertThat(page.getMetadata().getTotalElements()).isEqualTo(21);
		assertThat(page.getMetadata().getTotalPages()).isEqualTo(7);

		// Test the filter, create two more author
		createMocKAuthor(23, "ccarnaud", LASTNAME);
		createMocKAuthor(24, FIRSTNAME, "ddbrochain");
		// Test Firstname filter
		result = this.restTemplate.exchange("/authors?size=3&firstName=cc", HttpMethod.GET, null, getTypeRef());
//		result = this.restTemplate.getForEntity("/authors?size=3&firstName=cc", PagedResources.class);
		System.out.println("result.getBody() ==> :" + result.getBody());
		// Response body must not be null
		page = result.getBody();
		assertThat(page).isNotNull();
		assertThat(page.getMetadata().getTotalElements()).isEqualTo(1);
		assertThat(page.getMetadata().getTotalPages()).isEqualTo(1);
		Collection<AuthorResource> content = (Collection<AuthorResource>) page.getContent();
		content.forEach(item -> assertThat(item.getFirstName()).startsWith("cc"));
		// Test Lastname filter
		result = this.restTemplate.exchange("/authors?size=3&lastName=dd", HttpMethod.GET, null, getTypeRef());
//		result = this.restTemplate.getForEntity("/authors?size=3&lastName=dd", PagedResources.class);
		// Response body must not be null
		page = result.getBody();
		assertThat(page).isNotNull();
		assertThat(page.getMetadata().getTotalElements()).isEqualTo(1);
		assertThat(page.getMetadata().getTotalPages()).isEqualTo(1);
		for (AuthorResource authorResource : page.getContent()) {
			assertThat(authorResource.getLastName()).startsWith("dd");
		}

		// Finaly test all filter together : must be no result !
		result = this.restTemplate.exchange("/authors?size=3&lastName=dd&firstName=cc", HttpMethod.GET, null, getTypeRef());
//		result = this.restTemplate.getForEntity("/authors?size=3&lastName=dd&firstName=cc", PagedResources.class);
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

	private ParameterizedTypeReference<PagedResources<AuthorResource>> getTypeRef() {
		return new ParameterizedTypeReference<PagedResources<AuthorResource>>() {
		};
	}

}
