package com.jarvis.ca.jrvs.insurance_api.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document("people")
public class Person {
    private ObjectId id;
    private String firstName;
    private String lastName;
    private int age;
    private Address addressEntity;
    private Date createdAt = new Date();
    private Boolean insurance;
    private List<Car> carEntities;

    // Constructors
    public Person(){}
    public Person(String firstName, String lastName, int age, Address addressEntity, Date createdAt, Boolean insurance, List<Car> carEntities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.addressEntity = addressEntity;
        this.createdAt = createdAt;
        this.insurance = insurance;
        this.carEntities = carEntities;
    }

    // Getters and Setters

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(Address addressEntity) {
        this.addressEntity = addressEntity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getInsurance() {
        return insurance;
    }

    public void setInsurance(Boolean insurance) {
        this.insurance = insurance;
    }

    public List<Car> getCarEntities() {
        return carEntities;
    }

    public void setCarEntities(List<Car> carEntities) {
        this.carEntities = carEntities;
    }

    // ToString, Hashcode, Equals

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", addressEntity=" + addressEntity +
                ", createdAt=" + createdAt +
                ", insurance=" + insurance +
                ", carEntities=" + carEntities +
                '}';
    }
}
