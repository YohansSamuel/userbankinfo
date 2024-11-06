# CRUD Application with Spring Boot and CockroachDB

## Overview
This application manages users and their bank information with secure encryption for sensitive data.

## Setup
1. Clone the repository.
2. Ensure you have CockroachDB running locally.
3. Update `application.properties` with your database credentials.
4. Set the `ENCRYPTION_KEY` environment variable.

## Running the Application
1. Build the application:
   ```bash
   mvn clean package
# CRUD Application with Spring Boot and CockroachDB

## Overview
This application is designed to manage users and their bank information securely, utilizing encryption for sensitive data. It supports basic CRUD (Create, Read, Update, Delete) operations, making it easy to manage user accounts effectively.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Setup](#setup)
   - [Clone the Repository](#1-clone-the-repository)
   - [Set Up CockroachDB](#2-set-up-cockroachdb)
   - [Update Application Properties](#3-update-application-properties)
   - [Set Environment Variables for Encryption](#4-set-environment-variables-for-encryption)
- [Running the Application](#running-the-application)
- [Sample API Requests](#sample-api-requests)
- [Testing the Application](#testing-the-application)
- [Environment Variables](#environment-variables)
- [Additional Notes](#additional-notes)


## Prerequisites
Before you begin, ensure you have the following installed on your machine:
- **Java JDK**: Version 11 or higher. You can download it from [Oracle's website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or use a package manager.
- **Maven**: For building and running the application. Follow the installation guide on the [Maven website](https://maven.apache.org/install.html).
- **CockroachDB**: A distributed SQL database. Instructions for installation can be found [here](https://www.cockroachlabs.com/docs/v22.2/install-cockroachdb.html).

## Setup

### 1. Clone the Repository
Clone this repository to your local machine using the following command:
```bash
git clone https://github.com/YohansSamuel/userbankinfo.git
cd userbankinfo
```
### 2 Set Up CockroachDB
Start the CockroachDB server locally:
```bash
cockroach start --insecure
```
Create a new database for the application:
```bash
cockroach sql --insecure --execute="CREATE DATABASE finance_track_db;"
```
## Update Application Properties
Update the src/main/resources/application.properties file with your CockroachDB connection details:

properties
```
spring.datasource.url=jdbc:cockroachdb://localhost:26257/finance_track_db?sslmode=disable
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```
## Running the Application
Build the application:
```
mvn clean package 
```
Run the application:
```
java -jar target/userbankinfo.jar
```

## API Endpoints
The application exposes the following RESTful API endpoints for managing users:

```
Method	Endpoint	Description
POST	/api/users	Create a new user
GET	/api/users/{id}	Retrieve user information by ID
PUT	/api/users/{id}	Update user information
DELETE	/api/users/{id}	Delete user by ID
GET	/api/users	Retrieve a list of all users
```
The application exposes the following RESTful API endpoints for managing bank information:
```
Method	Endpoint	Description
POST	/users/{userId}/bank-information	Add bank information for a specific user.
GET	/users/{userId}/bank-information	Retrieve bank information for a specific user.
GET /users/123e4567-e89b-12d3-a456-426614174000/bank-information?page=0&size=5 Retrive pageable bank information

PUT	/users/{userId}/bank-information	Update existing bank information for a specific user.
DELETE	/users/{userId}/bank-information	Delete bank information for a specific user.
```
## Sample Requests
Create User
```
POST /api/users
Content-Type: application/json

{
"name": "Abebe Kebede",
"email": "abebekebede@example.com",
"bankAccount": "123456789"
}
```
Read User
```
GET /api/users/{id}
```
Update User
```
PUT /api/users/{id}
Content-Type: application/json

{
"name": "Abebe Kebede",
"email": "abebekebede@example.com",
"bankAccount": "987654321"
}
```
Delete User
```
DELETE /api/users/{id}
```
List Users
```
GET /api/users
```
## Security
The application utilizes encryption for sensitive data such as bank account details. Ensure the ENCRYPTION_KEY is securely managed and not exposed in your source code.

## Testing the Application
   The application includes unit and integration tests to ensure its functionality and reliability. You can run the tests using:
```
mvn test
```
### Environment Variables

To run this application, create a `.env` file in the root directory with the following variables:

```plaintext
SPRING_DATASOURCE_URL=jdbc:postgresql://cockroachdb:26257/finance_track_db?sslmode=disable
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=
SPRING_JPA_HIBERNATE_DDL_AUTO=update
COCKROACH_MAX_OFFSET=500ms
CR_ENCRYPTION_KEY=your-encryption-key
```

## Additional Notes
   #### Dependencies: 
        The application uses several dependencies including Spring Boot Starter Web, Spring Data JPA, and CockroachDB JDBC Driver.
   #### 
        Development Tools: Recommended tools for development include IntelliJ IDEA or Eclipse, with Maven for dependency management.