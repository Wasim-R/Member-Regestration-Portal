
# Member Registration Portal (MRP)

## Overview

Member Registration Portal (MRP) is a Spring Boot application designed to manage member registrations and insurance claim submissions. The system allows members to register, update their profiles, log in, and submit claims. Data is persisted in MongoDB using Spring Data MongoDB.

---

## Features

### Member Management

* Register new members
* Retrieve member details by ID
* Update member information
* Member login authentication

### Claim Management

* Submit insurance claims
* Verify member existence before claim processing

### Exception Handling

* Resource Not Found handling
* Data Validation handling
* Global exception management

---

## Technology Stack

### Backend

* Java 11
* Spring Boot 2.5.3
* Spring Web
* Spring Data MongoDB
* Lombok

### Database

* MongoDB

### Build Tool

* Maven

### Deployment

* Embedded Tomcat
* WAR Packaging Support

---

## Project Structure

```text
src/main/java/com/training/mrp
│
├── controller
│   ├── MemberController
│   └── ClaimController
│
├── service
│   ├── MemberService
│   ├── MemberServiceI
│   ├── ClaimService
│   └── ClaimServiceI
│
├── repository
│   ├── MemberRepositoryI
│   ├── ClaimRepositoryI
│   └── DependentRepositoryI
│
├── model
│   ├── Member
│   ├── Claim
│   ├── Dependent
│   ├── Address
│   └── Login
│
├── exception
│   ├── ResourceNotFoundException
│   ├── DataMissingException
│   ├── ClaimAlreadyExistException
│   └── GlobalExceptionHandler
│
└── MrpApplication
```

---

## Domain Models

### Member

Represents a registered member.

Fields:

```java
Integer id
String name
String dateOfBirth
Integer age
Long contactNumber
String panNumber
String email
String password
Address address
List<Dependent> dependents
```

### Dependent

Represents a dependent associated with a member.

```java
Integer id
String name
String dateOfBirth
```

### Claim

Represents a medical insurance claim.

```java
Integer id
String name
String dateOfBirth
String dateOfAdmission
String dateOfDischarge
String billAmmount
Integer claimMemberId
```

---

## Configuration

### application.properties

```properties
server.port=8081

spring.data.mongodb.uri=mongodb://localhost:27017/mrp
```

### MongoDB Setup

Start MongoDB locally:

```bash
mongod
```

Create database:

```text
mrp
```

Collections will be created automatically by Spring Data MongoDB.

---

## Running the Application

### Clone Repository

```bash
git clone https://github.com/Wasim-R/Member-Regestration-Portal.git
```

```bash
cd Member-Regestration-Portal/mrp
```

### Build Project

```bash
mvn clean install
```

### Run Application

```bash
mvn spring-boot:run
```

Or:

```bash
java -jar target/mrp-0.0.1-SNAPSHOT.war
```

Application starts on:

```text
http://localhost:8081
```

---

# REST APIs

## Register Member

### Request

```http
POST /api/member/register
```

### Sample Request

```json
{
  "id": 1,
  "name": "John Doe",
  "dateOfBirth": "1995-05-20",
  "age": 29,
  "contactNumber": 9876543210,
  "panNumber": "ABCDE1234F",
  "email": "john@example.com",
  "password": "password123"
}
```

### Response

```json
{
  "id": 1,
  "name": "John Doe"
}
```

---

## Find Member By ID

### Request

```http
GET /api/member/find/{id}
```

### Example

```http
GET /api/member/find/1
```

---

## Update Member

### Request

```http
PUT /api/member/update/{id}
```

### Example

```http
PUT /api/member/update/1
```

---

## Member Login

### Request

```http
POST /api/member/login
```

### Sample Request

```json
{
  "username": "john@example.com",
  "password": "password123"
}
```

---

## Submit Claim

### Request

```http
POST /api/submitClaim
```

### Sample Request

```json
{
  "id": 101,
  "name": "John Doe",
  "dateOfBirth": "1995-05-20",
  "dateOfAdmission": "2024-01-10",
  "dateOfDischarge": "2024-01-15",
  "billAmmount": "50000",
  "claimMemberId": 1
}
```

### Response

```text
Claim successfully created
```

---

## Check Member Eligibility

### Request

```http
GET /api/checkMember/{id}
```

### Example

```http
GET /api/checkMember/1
```

---

## Error Handling

The application provides centralized exception handling for:

### Resource Not Found

```json
{
  "message": "Member not found with the id : 1"
}
```

### Invalid Credentials

```json
{
  "message": "Invalid Credentials"
}
```

### Missing Data

```json
{
  "message": "Mandatory data is missing"
}
```

---

## Future Improvements

* JWT Authentication
* Role-Based Access Control (RBAC)
* Swagger/OpenAPI Documentation
* Input Validation using Bean Validation
* Docker Support
* Kubernetes Deployment
* Audit Logging
* Claim Approval Workflow
* Email Notifications
* Unit & Integration Testing

---

## Author

**Wasim Raja**

GitHub Repository:

https://github.com/Wasim-R/Member-Regestration-Portal

---

## License

This project is intended for learning and training purposes.
