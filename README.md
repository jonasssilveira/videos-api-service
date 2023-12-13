# Video Api

Web application deal with videos and its metadata.

## Overview

This project follows the Hexagonal Architecture pattern, also known as Ports and Adapters or Onion Architecture. The goal of this architectural style is to decouple the core business logic from the external components.

## Structure

The Hexagonal Architecture revolves around three main concepts:

### 1. Domain Logic

The heart of the application resides here. It contains entities, use cases, and business logic that are independent of external concerns. This layer remains agnostic of any specific implementation details.

### 2. Ports

Ports are interfaces or contracts that define the interactions between the domain and the external world. These are interfaces that represent the use cases provided by the application. For instance, a repository interface represents the means to access the database without any actual implementation.

### 3. Adapters

Adapters implement the ports. They serve as the bridge between the application's core (domain logic) and the external world (frameworks, databases, UI, etc.). Adapters are responsible for translating external inputs to the application and vice versa.

## Benefits

### 1. Testability

The Hexagonal Architecture enhances testability by allowing the domain to be tested independently of external dependencies. This is achieved through the use of ports and dependency inversion.

### 2. Maintainability

The separation of concerns facilitates maintainability. Changes in external systems or libraries can be accommodated without affecting the core business logic.

### 3. Flexibility

The architecture's modularity enables easy integration of new components or changes without disturbing the existing structure.

## Project Structure

### Controllers

#### MetadataController

The MetadataController handles HTTP requests related to metadata. It interacts with the MetadataService to fetch and manipulate metadata information. This controller primarily handles the CRUD operations related to metadata.

#### VideoController

The VideoController manages HTTP requests related to videos. It interfaces with the VideoService to handle video-related functionalities. It provides endpoints to manage videos, such as creation, deletion, and retrieval.

### Infrastructure

#### SwaggerConfig

SwaggerConfig enables and configures Swagger for API documentation. It provides a structured way to document the APIs exposed by the application, making it easier for developers to understand and use the endpoints.

### Repository Layer

#### MetadataJpaRepository

MetadataJpaRepository is responsible for managing metadata entities in the database. It extends the JpaRepository and provides various methods to interact with the underlying database, such as saving, querying, updating, and deleting metadata records.

#### VideoJpaRepository

The VideoJpaRepository deals with the persistence of video entities in the database. It extends the JpaRepository and offers methods to perform CRUD operations on video records in the database.

#### StaffJpaRepository

StaffJpaRepository handles the database interactions related to staff entities. It extends JpaRepository and provides functionalities to manage staff records in the database.

### Repository Adapter Layer

#### MetadataRepositoryAdapter

The MetadataRepositoryAdapter acts as an intermediary layer between the Repository layer and the Service layer. It contains the business logic related to metadata before interacting with the data access layer. It transforms entities to DTOs and vice versa, providing a cleaner separation between layers.

#### VideoRepositoryAdapter

Similar to MetadataRepositoryAdapter, VideoRepositoryAdapter serves as a bridge between the Video repository and the Service layer. It encapsulates the logic for video-related operations and transforms entities into DTOs.

### Service Layer

#### MetadataService

The MetadataService manages metadata-related business logic. It takes inputs from controllers or other services, manipulates metadata entities, and communicates with the repository adapter layer for CRUD operations. This layer is responsible for transforming entities into DTOs for output.

#### VideoService

VideoService handles video-related operations and encapsulates the business logic associated with video functionalities. It interacts with the repository adapter layer to perform CRUD operations and converts entities to DTOs for output.

### Why DTOs for Input and Output?

DTOs (Data Transfer Objects) are used for input and output between layers. They act as data carriers that transport data between the presentation (or controller) layer and the service layer, or even between services.

#### Benefits of Using DTOs:

1. **Encapsulation**: DTOs encapsulate data exchanged between different layers, preventing the direct exposure of internal data structures.

2. **Flexibility**: They provide a flexible way to define the data exchanged, allowing different representations for different layers or clients.

3. **Reduced Network Calls**: DTOs enable bundling multiple data items into a single object, reducing the number of network calls required for data transfer.

4. **Versioning**: They help in versioning data contracts, allowing different versions of the same API to coexist without breaking changes.

5. **Security and Validation**: DTOs can include validation logic for data coming into the application, ensuring its correctness and security.

DTOs are particularly useful in web applications where you might have different representations for the same entity depending on the view.

### Migrations and Flyway Integration

#### Migrations

Migration files contain a set of SQL scripts that represent incremental changes to the database schema. They play a crucial role in managing database schema versions in a systematic way. Each migration script represents a single change to the database, enabling version control of the database schema.

#### Flyway Integration in Docker Compose

This project utilizes Flyway, a database migration tool, to manage database schema changes seamlessly. Flyway integrates into the project's Docker Compose setup, ensuring that the necessary migrations are executed automatically during the project's build phase.

Flyway's integration simplifies database management by automatically applying migrations to the database, ensuring that the database schema is in sync with the codebase.

### How It Works

1. **Migration Files**: Inside the `/migration` directory, you'll find SQL files named in a versioned manner. Each file represents a single change to the database schema. For instance, `V1__Create_Table.sql` might contain the SQL script for creating a new table.

2. **Docker Compose with Flyway**: The `docker-compose.yml` file includes a service for the database setup. Flyway is integrated into this setup, allowing the migrations to be applied automatically during the Docker build process.

## Testing

### Unit Tests

- The project includes a suite of unit tests that focus on testing individual components or functions in isolation.
- These tests verify the behavior of small units of code to ensure they work as expected.
- They are executed without external dependencies or interactions, often using mocking frameworks for isolated testing.

### Integration Tests

- The project also incorporates integration tests that validate the interaction between different modules or components.
- These tests simulate real-world scenarios and interactions among multiple parts of the application, including database interactions.
- To mimic database interactions without affecting the actual database, the tests utilize mocking or stubbing techniques, ensuring controlled and predictable behavior.

### Mocked Database Returns

- The integration tests involving database interactions use mocking techniques to simulate database responses.
- Instead of directly interacting with the actual database, these tests utilize mock objects or frameworks to mimic database behavior.
- By simulating database returns, these tests maintain independence from the live database, ensuring consistent and repeatable test results.

#### Example:

- The integration tests utilize mocking frameworks like Mockito or similar techniques to mock database returns.
- Mocking allows us to simulate various database scenarios, ensuring thorough testing of the application's logic without affecting the database's state.
```console
$ mvn test
``` 

## Prerequisites

To run the project, the following software must be installed on the system:

- [Java](https://www.oracle.com/java/) (v17)
- [Maven](https://maven.apache.org/) (v3.9.0 or higher)

### Building with Maven

To build the project, navigate to the project root directory and execute the following commands in your terminal:

#### With Tests

```bash
$ mvn clean install
```

This command will clean the project, compile the source code, run tests, and package the application.

#### Without Tests

To build the project without running tests:

```bash
$ mvn clean install -DskipTests
```

## To Run Docker

```bash
$ docker-compose up
```

Note:

- This process may take a significant amount of time.
- If [Docker](https://www.docker.com/) is not installed, please install it or follow the steps below.

## Swagger Documentation

The API endpoints are documented using Swagger. You can explore and test the endpoints using the Swagger UI interface. Access the Swagger documentation by navigating to [http://localhost:8081/video-streaming-api/swagger-ui/](http://localhost:8081/video-streaming-api/swagger-ui/#/).

Swagger provides an interactive interface to:

- View all available endpoints.
- Understand the request methods, parameters, and response structures for each endpoint.
- Test the endpoints directly within the browser.

Explore and interact with the API endpoints using the Swagger UI.

### Postman Collection

Find the Postman collection in the `request-example` directory, which contains request examples for the API endpoints. Import this collection into Postman to interact with the API.

The collection includes the following:

- Request examples for various endpoints such as GET, POST, PUT, DELETE.
- Request headers, body, and parameters configured for testing.

### Using Postman

1. Download and install Postman (if you haven't already).
2. Import the `request-example` Postman collection into Postman.
3. Modify the request URLs or payloads as needed for testing.
4. Send requests to interact with the API endpoints.

Make sure to update request details (e.g., URLs, headers, body) in Postman according to your environment or specific configurations.

## Bookmarks

- Link to the [Web Application](http://localhost:8081/video-streaming-api)
- Link to the [API Documentation](http://localhost:8081/video-streaming-api/swagger-ui/#/)

Note:

- To view the API documentation, the backend must be running.

## License
This project is licensed under the [MIT License](LICENSE).
