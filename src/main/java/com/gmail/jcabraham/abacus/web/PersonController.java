package com.gmail.jcabraham.abacus.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

import org.apache.commons.logging.*;
import com.gmail.jcabraham.abacus.domain.Person;
import com.gmail.jcabraham.abacus.service.PersonRepository;

@RestController
public class PersonController {
	
	@Autowired
	PersonRepository personRepos;
	
    Log log = LogFactory.getLog(PersonController.class);
 
    @GetMapping("/resources/data/list")
    public Iterable<Person> getPeople() {
    	log.debug("GET /resources/data populate");
    	return personRepos.findAll();
    }
    
    @GetMapping("/resources/data/{data_id}")
    public Person getPerson(@PathVariable("data_id") Long id) {
    	log.debug("GET /resources/data/" + id);
    	try {
    		Person p = personRepos.findById(id).get();
    		log.info("retrieved person: " + p.toString());
    		return p;
    	} catch (NoSuchElementException e) {
    		throw new AbacusException(HttpStatus.NOT_FOUND, "Person not found for id: " + id, e);
    	} catch (IllegalArgumentException ie) {
    		throw new AbacusException(HttpStatus.NOT_FOUND, "Please provide an id", ie);
    	}
    }
    
    @PutMapping("/resources/data")
    @ResponseStatus(HttpStatus.CREATED)
	Person createPerson(@RequestBody Person person) {
	    log.debug("PUT /resources/data/");
	    try {
	    	Person newPerson = personRepos.save(person);
	    	log.info("created person: " + newPerson.toString());
	    	return newPerson;
	    } catch (Exception e) {
	    	throw new AbacusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating person record: " + e.getMessage(), e);
	    }
	}
    
	@DeleteMapping("/resources/data/{data_id}")
	Person removePerson(@PathVariable("data_id") Long id) {
		log.debug("DELETE /resources/data/" + id);
		try {	
			Person p = personRepos.findById(id).get();
			personRepos.deleteById(p.getId());
			return p;
	    } catch (Exception e) {
	    	throw new AbacusException(HttpStatus.NOT_FOUND, "Person not found for id: " + id, e);
	    }
	}
}
