package com.gmail.jcabraham.abacus;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.*;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.commons.logging.*;

import com.gmail.jcabraham.abacus.domain.Person;
import com.gmail.jcabraham.abacus.service.PersonRepository;
import com.gmail.jcabraham.abacus.web.PersonController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AbacusApplicationTests {
	
    Log log = LogFactory.getLog(AbacusApplicationTests.class);
    
    @LocalServerPort
    private int port;
 
    @Autowired
    private TestRestTemplate testRestTemplate;
    
	@Autowired
	PersonController controller;

	@Autowired 
	PersonRepository repository;
	


	
	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void testGetOK() throws Exception {
		URI url = new URI("http://localhost:" + port + "/resources/data/list");
		ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);
		log.debug("YOYOYO: LIST" + response.getBody());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void testPersonNotFound() throws Exception {
		URI url = new URI("http://localhost:" + port + "/resources/data/" + Integer.MAX_VALUE);
		ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void testGetPerson() throws Exception {
		Person p = repository.save(new Person("Test Person", 99, "en_US"));
		URI url = new URI("http://localhost:" + port + "/resources/data/" + p.getId());
		
		ResponseEntity<Person> response = testRestTemplate.getForEntity(url, Person.class);
		assertThat(response.getBody().getId()).isEqualTo(p.getId());
	}
	
	@Test
	public void testPutPerson() throws Exception {
		URI url = new URI("http://localhost:" + port + "/resources/data");
		Person body = new Person("Foo Person", 30, "us_EN");
		RequestEntity<Person> request = RequestEntity
				.put(url)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(body);
		
		Person p = testRestTemplate.exchange(request, Person.class).getBody();
		assertThat(p.getId()).isNotNull();
	}
	
	@Test
	public void testDeletePerson() throws Exception {
		Person p = repository.save(new Person("Test Person", 99, "en_US"));
		URI url = new URI("http://localhost:" + port + "/resources/data/" + p.getId());
		
		RequestEntity<Person> request = RequestEntity
				.method(HttpMethod.DELETE, url)
				.body(p);
		
		Person deleted = testRestTemplate.exchange(request, Person.class).getBody();
		assertThat(deleted.getId()).isEqualTo(p.getId());
	}
}
