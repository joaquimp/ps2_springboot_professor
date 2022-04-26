### Prerequisites

- This application uses [Spring Boot Web Starter](https://spring.io/guides/gs/spring-boot/)
- This application is based on the [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/) tutorial
- This application uses Java 1.8
- This application uses Maven for building and dependency management

### Application Structure

- Files must be located under `/src/main/java` to be picked up by Spring
- The demo source code is located in `src/main/java/com/example/restservice` with folders for:
  - `controllers`
  - `data`
  - `models`
- The project assumes `com.example.restservice.DemoApplication` is the Spring Boot Application. To change this, remove the `@SpringBootApplication` annotation from `DemoApplication` and add it to another application class.

### Running the app

Press the "Run" button

-- OR -- 

From the Terminal in repl.it, run:

```
mvn clean package

java -jar target/restservice-0.0.1-SNAPSHOT.jar
```

A subwindow displaying a browser will appear, showing you the output. You may copy the URL and open it in a new browser tab to change the URL, e.g. adding `/greeting` to the end of the URL for the sample app will display the "Hello World" JSON.

### Endpoints

The base project has some endpoints you can build on: 

#### Basic endpoints

These endpoints simply display a string. They are defined in the `GreetingController`:

- `/` - a "Welcome to the Spring Boot Application Starter!" default page 
- `GET /greeting` - a GET endpoint that takes an optional `name` String parameter and returns a JSON `Greeting` object
- `POST /greeting` - a POST endpoint that takes a JSON body of the `Person` object with a name, and returns a JSON `Greeting` object

#### REST endpoints

##### Controller defined REST endpoints
These endpoints use common REST terms - `GET`, `POST`, `DELETE` - to interact with data.

To create REST endpoints for interacting with your data, you can create a `RestController` and define each of the endpoints. This creates more boilerplate code, but it allows more control over the endpoints and the returned data.

`CustomerController` contains endpoints for the `Customer` model:
- `GET /customers` - returns a JSON array of all `customers` in the database
- `GET /customers/{id}` - returns a single JSON object of the customer with the specified `id`
- `POST /customers` - takes a JSON object of a single customer as the request body and adds it to the `customers` database table
- `DELETE /customers/{id}` - removes the customer with the given `id` from the `customers` database

##### Auto-created REST endpoints

Spring can create REST endpoints automatically by implementing `org.springframework.data.repository.CrudRepository`. This requires much less code, but defaults to Spring's handling of responses, where the JSON representation of your data is in a nested structure and includes metadata.

`ItemRepository` uses Spring's `CrudRepository` to automatically create REST endpoints for the `Item` model:
- `GET /items` - returns a JSON array of all `items` in the database
- `GET /items/{id}` - returns a single JSON object of the item with the specified `id`
- `POST /items` - takes a JSON object of a single item as the request body and adds it to the `items` database table
- `DELETE /items/{id}` - removes the item with the given `id` from the `items` database table

### Database

This project uses `spring-boot-starter-data-jpa`, `spring-boot-starter-data-rest`, and `hsqldb` to create a HyperSQL Database (HSQLDB) in-memory with REST endpoints.

#### Database configuration
The database is configured using the `/src/main/resources/application.properties` file.

#### Database security
- Use your repl's "Secrets" to create environment variables for `DB_USERNAME` and `DB_PASSWORD`. These will be used during the HSQLDB instantiation via the `/src/main/resources/application.properties` file.

#### Data model initialization
The HSQLDB instance is stored in-memory, but the data model(s) can be initialized using the SQL script located at `/src/main/resources/import.sql`. This file can contain any standard SQL statements you need to initialize your database.

**NOTE:** Any changes you make to the data in the database will be lost when your repl spins down. If you want to persist data changes for longer, consider setting your repl to _"Always On"_.

### Troubleshooting

- If the terminal window gets stuck in a loop while trying to run a `java` command, refresh the repl.it browser window for this project.