# Module Management

### Stack

- Language: Java 8
- Framework: Springboot 2
- Library Management: Maven
- Database: MySQL

### Setup

1. Import project in IDEA (I use eclipse)
2. Set database configuration in application.properties
3. Run Springboot Application (DDL SQL script will be executed automatically)
4. Insert sample data using SQL script (location: src/main/resources/sql-script/data.sql)
5. Open swagger to test API (<http://localhost:8080/module-service/swagger-ui.html>)

### Application Overview

1. Contains 3 Controller
   - Module Controller
   - User Controller
   - User Group Controller

2. Main API feature: API Return all modules assigned to User

3. Other API support features:
   - Assign User to a User Group
   - Assign Module for User Group
   - Create, Read and Update operation for UserGroup
   - Create, Read and Update operation for User

4. Unit test provided for Service and Controller level

5. Swagger for API documentation and simplifying API testing

6. Code Structure / Packaging:
   - Entity for database modelling
   - JPA Repository for Data Access
   - Service for logic level
   - Controller serving request and providing response
   - Controller Advice for handling any exception returned to user
   - Error code standarization and categorization to provide readable error message
   - Request and response model, generic response model to standardize API response

### Thank You
