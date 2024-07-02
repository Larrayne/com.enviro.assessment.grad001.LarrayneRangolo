# Waste Management Application

This is a Spring Boot application for managing waste categories, 
disposal guidelines,and recycling tips.It provides RESTful 
APIs for frontend applications to interact with the backend server,
allowing functionalities such as waste category lookup,
disposal guidelines retrieval, and recycling tips display.

## Features

- Add, update, delete, and retrieve waste categories
- Retrieve disposal guidelines
- Retrieve recycling tips
- JSON-formatted responses
- Input validation using Spring Boot validation annotations

## Requirements

- Java 17
- Maven 3.6+


## Getting Started

### Clone the Repository

```sh
git clone https://github.com/yourusername/waste_management.git
cd waste_management

## Build and Run the Application
mvn clean install
mvn spring-boot:run

The application will start on http://localhost:8080.

## To run different controllers  

curl -X GET http://localhost:8080/api/waste-categories
curl -X POST http://localhost:8080/api/waste-categories \
     -H "Content-Type: application/json" \
     -d '{"name": "Metal", "description": "Items made of metal"}'
curl -X PUT http://localhost:8080/api/waste-categories/1 \
     -H "Content-Type: application/json" \
     -d '{"name": "Updated Metal", "description": "Updated items made of metal"}'
curl -X DELETE http://localhost:8080/api/waste-categories/{id}



