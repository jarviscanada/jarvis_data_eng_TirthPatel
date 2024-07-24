# Introduction
This project serves to demonstrate the implementation of a simple back end insurance application. Insurance companies use such applications to manage their data, which can be used to extract valuable insights for their business needs. Users can use this Spring Boot application through various API endpoints, depending on the use case. All data is saved on a MongoDB NoSQL instance. The technologies used to implement this application include Spring Boot, MongoDB, and Postman (testing API endpoints). 


# Setup
## Prerequisites
- MongoDB server should be installed
- MongoDB compass should be installed (GUI for MongoDB Server)
- Postman should be installed for testing APIs
## Quick Start
1. Run MongoDB Server (should be serving on localhost:27017)
2. Connect to MongoDB Server using MongoDB Compass and create a new database named `insurance`
3. Build and run the SpringBoot application
4. Explore the different enpoints for this application (See below)

# Endpoints

1. `Create new Person` Creates new person entity
- URL: `http://localhost:8080/insurance_api/person`
- HTTP Method: `POST`
- Body:
```
{
  "firstName": "Nevil",
  "lastName": "Sintho",
  "age": 50,
  "addressEntity": {
    "streetNumber": 76,
    "streetName": "Havelock Gate",
    "city": "Markham",
    "country": "Canada",
    "postalCode": "L3S 3X7"
  },
  "insurance": true,
  "carEntities": [
    {
      "make": "Honda",
      "model": "Civic",
      "maxSpeed": 50
    }
  ]
}
```

2. `Create new people` Creates new entity for each person document
- URL: `http://localhost:8080/insurance_api/people`
- HTTP Method: `POST`
- Body:
```
[{
  "firstName": "Nevil",
  "lastName": "Sintho",
  "age": 50,
  "addressEntity": {
    "streetNumber": 76,
    "streetName": "Havelock Gate",
    "city": "Markham",
    "country": "Canada",
    "postalCode": "L3S 3X7"
  },
  "insurance": true,
  "carEntities": [
    {
      "make": "Honda",
      "model": "Civic",
      "maxSpeed": 50
    }
  ]
}]
```

3. `Get people of specified Ids` Gets entities of people that have user specified Ids
- URL: `http://localhost:8080/insurance_api/people/ids`
- HTTP Method: `GET`
- Body:
```
[
"668c1283f09dd91d5dffc546"
]
```

4. `Get all people` Gets entities of all people
- URL: `http://localhost:8080/insurance_api/people`
- HTTP Method: `GET`

5. `Get person with a specified Id` Gets entity of person that has a user specified Id
- URL: `http://localhost:8080/insurance_api/person/668c1283f09dd91d5dffc546`
- HTTP Method: `GET`

6. `Delete person with a specified Id` Delete entity of person that has a user specified Id
- URL: `http://localhost:8080/insurance_api/person/668c1283f09dd91d5dffc546`
- HTTP Method: `DELETE`

7. `Delete people of specified Ids` Delete entities of people that have user specified Ids
- URL: `http://localhost:8080/insurance_api/people/ids`
- HTTP Method: `DELETE`
- Body:
```
[
"668c1283f09dd91d5dffc546"
]
```

8. `Delete all people` Deletes entities of all people
- URL: `http://localhost:8080/insurance_api/people`
- HTTP Method: `DELETE`

9. `Get number of people` Gets a count of all people entities
- URL: `http://localhost:8080/insurance_api/count`
- HTTP Method: `GET`

10. `Get average age of people` Gets average age of all people entities
- URL: `http://localhost:8080/insurance_api/people/averageAge`
- HTTP Method: `GET`

11. `Get maximum amount of cars owned by any person` Gets max car count of all people entities
- URL: `http://localhost:8080/insurance_api/people/maxCars`
- HTTP Method: `GET`
