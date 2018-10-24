package com.gmail.jcabraham.abacus.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.gmail.jcabraham.abacus.domain.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

	List<Person> findByName(String name);
}
