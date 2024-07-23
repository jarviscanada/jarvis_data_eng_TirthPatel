package com.jarvis.ca.jrvs.insurance_api.repository;

import com.jarvis.ca.jrvs.insurance_api.model.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<Person, ObjectId> {
    @Aggregation(pipeline = {
            "{'$count':'total'}"
    })
    long getCount();

    @Aggregation(pipeline = {
            "{ '$group': { '_id': null, 'averageAge': { '$avg': '$age' } } }"
    })
    Double getAverageAge();

    @Aggregation(pipeline = {
            "{ '$project': { 'numCars': { '$size': '$carEntities' } } }",
            "{ '$group': { '_id': null, 'maxCars': { '$max': '$numCars' } } }",
            "{ '$project': { '_id': 0, 'maxCars': 1 } }"
    })
    Integer getMaxCars();


}
