
package com.gmail.jcabraham.abacus.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	

	private String name;
	
	private Integer age;
	
	private String locale;
	
	protected Person() {}
	
	public Person(String name, Integer age, String locale) {
		this.name = name;
		this.age = age;
		this.locale = locale;
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Integer getAge() {
		return age;
	}
	public String getLocale() {
		return locale;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	@Override
	public String toString() {
		return "id: ${id}, name: ${name}";
	}
	
	public static Person makePerson(Long id, String name, Integer age, String locale) {
		Person p = new Person();
		p.id = id;
		p.name = name;
		p.age = age;
		p.locale = locale;
		return p;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Person) {
			Person p = (Person)o;
			return p.getId().equals(this.id) &&
					p.getName().equals(this.name) &&
					p.getAge().equals(this.age) &&
					p.getLocale().equals(this.locale);
		} else return false;			
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
