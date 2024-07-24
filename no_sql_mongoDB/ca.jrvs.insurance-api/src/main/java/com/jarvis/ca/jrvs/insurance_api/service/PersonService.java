package com.jarvis.ca.jrvs.insurance_api.service;

import com.jarvis.ca.jrvs.insurance_api.model.Person;
import com.jarvis.ca.jrvs.insurance_api.repository.PersonRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private final PersonRepository repo;

    public PersonService(PersonRepository repo) {
        this.repo = repo;
    }

    public void save(Person person) {
        repo.save(person);
    }
    public void saveAll(List<Person> people) {
        people.forEach((p)->{
            save(p);
        });
    }
    public Optional<Person> findOne(ObjectId id) {
        return repo.findById(id);
    }
    public List<Person> findAll(List<ObjectId> ids) {
        List<Person> people= new ArrayList<>();
        ids.forEach(id->{
            Person person=repo.findById(id).get();
            people.add(person);
        });
        return people;
    }
    public List<Person> findAll() {
        return repo.findAll();
    }
    public void delete(ObjectId id) {
        repo.deleteById(id);
    }
    public void delete(List<ObjectId> ids) {
        ids.forEach(id->{
            delete(id);
        });
    }
    public void deleteAll() {
        repo.deleteAll();
    }
    public void update(Person person) {
        repo.save(person);
    }
    public void update(List<Person> people) {
        people.forEach(person->repo.save(person));
    }
    public long count() {return repo.getCount();}
    public double getAverageAge() {return repo.getAverageAge();}
    public int getMaxCars() {return repo.getMaxCars();}

}
