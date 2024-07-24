package com.jarvis.ca.jrvs.insurance_api.controller;

import com.jarvis.ca.jrvs.insurance_api.model.Person;
import com.jarvis.ca.jrvs.insurance_api.service.PersonService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/insurance_api")
public class PersonController {
    @Autowired
	private final PersonService service;
	
	public PersonController(PersonService service) {
		this.service = service;
	}
	
	@PostMapping("person")
	@ResponseStatus(HttpStatus.CREATED)
	public void postPerson(@RequestBody Person person) {
		service.save(person);
	}

	@PostMapping("people")
	@ResponseStatus(HttpStatus.CREATED)
	public void postPeople(@RequestBody List<Person> people) {
		service.saveAll(people);
	}
	
	@GetMapping("people/ids")
	public List<Person> getPeople(@RequestBody List<ObjectId> ids) {
		return service.findAll(ids);
	}

	@GetMapping("people")
	public List<Person> getPeople() {
		return service.findAll();
	}

	@DeleteMapping("person/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deletePerson(@PathVariable ObjectId id) {
		service.delete(id);
	}

	@DeleteMapping("people/ids")
	public void deletePeople(@RequestBody List<ObjectId> ids) {
		service.delete(ids);
	}

	@DeleteMapping("people")
	public void deletePeople() {
		service.deleteAll();
	}
	@GetMapping("count")
	public long getCount() {
		return service.count();
	}

	@GetMapping("people/averageAge")
	public double getAverageAge() {
		return service.getAverageAge();
	}
	@GetMapping("people/maxCars")
	public int getMaxCars() {
		return service.getMaxCars();
	}

	@GetMapping("person/{id}")
	public ResponseEntity<Person> getPerson(@PathVariable ObjectId id) {
		Optional<Person> o = service.findOne(id);
		if (o.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(o.get());
	}


    
}
