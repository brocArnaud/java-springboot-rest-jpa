/*
 * Created on 2017-12-20 ( Date ISO 2017-12-20 - Time 14:58:55 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package org.demo.controller.test;
import static org.assertj.core.api.Assertions.assertThat;

import org.demo.controller.resource.CountryResource;
import org.demo.entity.Country;
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
public class CountryControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	private static final String NAME = "name";

	@Test
	public void createCountryTest() {
		// Create an country
		Country country = createMocKCountry("1" , NAME);
		// Recover this country
		ResponseEntity<CountryResource> result = this.restTemplate.getForEntity("/country/1", CountryResource.class);
		// The response can't be null
		assertThat(result).isNotNull();
		// The status code must be OK
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		// Response body must not be null
		assertThat(result.getBody()).isNotNull();
		// Assert the Hateoas self link
		Link link = new Link("http://localhost:9999/country/1", Link.REL_SELF);
		assertThat(result.getBody().getId()).isEqualTo(link);
		assertThat(result.getBody().getName()).isEqualTo(NAME);
		// Try to create the same country, must return a CONFLICT status code
		result = this.restTemplate.postForEntity("/country", country, CountryResource.class);
		// The reponse can't be null
		assertThat(result).isNotNull();
		// The status code must be OK
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
		// Response body must not be null
		assertThat(result.getBody()).isNull();
	}

	@Test
	public void updateTest() {
		// Create an country and assert field
		Country country = createMocKCountry("1"  , NAME);
		// Recover this country
		ResponseEntity<CountryResource> result = this.restTemplate.getForEntity("/country/1", CountryResource.class);
		// The reponse can't be null
		assertThat(result).isNotNull();
		// The status code must be OK
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		// Response body must not be null
		assertThat(result.getBody()).isNotNull();
		// Assert the Hateoas self link
		Link link = new Link("http://localhost:9999/country/1", Link.REL_SELF);
		assertThat(result.getBody().getId()).isEqualTo(link);
		assertThat(result.getBody().getName()).isEqualTo(NAME);

		// Change field value
		country.setName("name");
		// Process update
		RequestEntity<Country> request = new RequestEntity<Country>(country, HttpMethod.PUT, null);
		ResponseEntity<Void> resultUpdate = this.restTemplate.exchange("/country/1", HttpMethod.PUT, request,
				getTypeRefVoid());
		// The response can't be null
		assertThat(resultUpdate).isNotNull();
		// The status code must be OK
		assertThat(resultUpdate.getStatusCode()).isEqualTo(HttpStatus.OK);

		// Recover the country and ensure field are correct
		result = this.restTemplate.getForEntity("/country/1", CountryResource.class);
		// The reponse can't be null
		assertThat(result).isNotNull();
		// The status code must be OK
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		// Response body must not be null
		assertThat(result.getBody()).isNotNull();
		// Assert the Hateoas self link
		link = new Link("http://localhost:9999/country/1", Link.REL_SELF);
		assertThat(result.getBody().getId()).isEqualTo(link);
		assertThat(result.getBody().getName()).isEqualTo("name");
	}

	@Test
	public void updateNotFoundTest() {
		// Process update
		RequestEntity<Country> request = new RequestEntity<Country>(new Country(), HttpMethod.PUT, null);
		ResponseEntity<Void> resultUpdate = this.restTemplate.exchange("/country/999", HttpMethod.PUT, request,
				getTypeRefVoid());
		// The response can't be null
		assertThat(resultUpdate).isNotNull();
		// The status code must be NOT_FOUND
		assertThat(resultUpdate.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	public void deleteTest() {
		// Create an country
		createMocKCountry("1"  , NAME);
		// Recover this country and test if all is ok
		ResponseEntity<CountryResource> result = this.restTemplate.getForEntity("/country/1", CountryResource.class);
		// The reponse can't be null
		assertThat(result).isNotNull();
		// The status code must be OK
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

		// Delete the country
		RequestEntity<Country> request = new RequestEntity<Country>(new Country(), HttpMethod.DELETE, null);
		ResponseEntity<Void> resultDelete = this.restTemplate.exchange("/country/1", HttpMethod.DELETE, request,
				getTypeRefVoid());
		// The response can't be null
		assertThat(resultDelete).isNotNull();
		// The status code must be NOT_CONTENT
		assertThat(resultDelete.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

		// Try to recover the country and ensure it's don't exist
		// Recover this country and test if all is ok
		result = this.restTemplate.getForEntity("/country/1", CountryResource.class);
		// The reponse can't be null
		assertThat(result).isNotNull();
		// The status code must be NOT_FOUND
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	public void deleteNotFound() {
		// Delete the country
		RequestEntity<Country> request = new RequestEntity<Country>(new Country(), HttpMethod.DELETE, null);
		ResponseEntity<Void> resultDelete = this.restTemplate.exchange("/country/999", HttpMethod.DELETE, request,
				getTypeRefVoid());
		// The response can't be null
		assertThat(resultDelete).isNotNull();
		// The status code must be NOT_FOUND
		assertThat(resultDelete.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	public void getCountrysTest() {
		// First call assert no element
		ResponseEntity<PagedResources<CountryResource>> result = this.search("/country");
		// The reponse can't be null
		assertThat(result).isNotNull();
		// The status code must be OK
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		// Response body must not be null
		PagedResources<CountryResource> page = result.getBody();
		assertThat(page).isNotNull();
		assertThat(page.getMetadata()).isNotNull();
		assertThat(page.getMetadata().getTotalElements()).isEqualTo(0);
		assertThat(page.getMetadata().getTotalPages()).isEqualTo(0);
		// By default page size is 20
		assertThat(page.getMetadata().getSize()).isEqualTo(20);

		// Insert 21 country
		for (int i = 1; i < 22; i++) {
			createMocKCountry(String.valueOf(i),String.valueOf(i));
		}
		result = this.search("/country");
		// Response body must not be null
		page = result.getBody();
		assertThat(page).isNotNull();
		assertThat(page.getMetadata().getTotalElements()).isEqualTo(21);
		assertThat(page.getMetadata().getTotalPages()).isEqualTo(2);

		// Change the number of element per page and assert the page number
		result = this.search("/country?size=3");
		// Response body must not be null
		page = result.getBody();
		assertThat(page).isNotNull();
		assertThat(page.getMetadata().getTotalElements()).isEqualTo(21);
		assertThat(page.getMetadata().getTotalPages()).isEqualTo(7);
	}

	@Test
	public void getCountrysTestFilter() {
		// Create an country
		Country country = createMocKCountry("1" , NAME);
		RequestEntity<Country> request = null;
		ResponseEntity<PagedResources<CountryResource>> result = null;
		PagedResources<CountryResource> page = null;
		// Test name filter
		// Set the value on entity and update
		country.setName("c");
		// Process update
		request = new RequestEntity<Country>(country, HttpMethod.PUT, null);
		this.restTemplate.exchange("/country/1", HttpMethod.PUT, request, getTypeRefVoid());
		// Test name filter
		result = this.search("/country?size=20&name=c");
		// Response body must not be null
		page = result.getBody();
		assertThat(page).isNotNull();
		assertThat(page.getMetadata().getTotalElements()).isEqualTo(1);
		assertThat(page.getMetadata().getTotalPages()).isEqualTo(1);
		for (CountryResource countryResource : page.getContent()) {
			assertThat(countryResource.getName()).startsWith("c");
		}
	}

	private Country createMocKCountry(String code, String name) {
		Country countryMock = new Country();
		countryMock.setCode(code);
		countryMock.setName(name);
		this.restTemplate.postForLocation("/country", countryMock);
		return countryMock;
	}

	private ResponseEntity<PagedResources<CountryResource>> search(String uri) {
		return this.restTemplate.exchange(uri, HttpMethod.GET, null, getTypeRef());
	}

	private ParameterizedTypeReference<PagedResources<CountryResource>> getTypeRef() {
		return new ParameterizedTypeReference<PagedResources<CountryResource>>() {
		};
	}

	private ParameterizedTypeReference<Void> getTypeRefVoid() {
		return new ParameterizedTypeReference<Void>() {
		};
	}
}