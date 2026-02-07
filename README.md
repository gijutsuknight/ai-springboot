# ai-springboot

Spring Boot application with context path `/ai-springboot`, Java 21, and Maven. Includes Swagger (OpenAPI) for API documentation and a test controller with GET and POST endpoints.

## Tech stack

- **Java 21**
- **Spring Boot 3**
- **Maven** (dependency management and build)
- **springdoc-openapi** (Swagger UI and OpenAPI 3)

## Prerequisites

- **JDK 21** – [Adoptium](https://adoptium.net/) or [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.6+** (3.9+ recommended) – [Maven](https://maven.apache.org/download.cgi)

### Verify installation

```bash
java -version   # Should show version 21
mvn -v          # Should show Maven 3.6 or higher
```

## Environment setup

1. Clone the repository and go to the project root:
   ```bash
   cd ai-springboot
   ```
2. (Optional) Set `JAVA_HOME` to your JDK 21 installation if not already set.

## Running locally

From the project root:

```bash
mvn spring-boot:run
```

- **Base URL:** http://localhost:8080/ai-springboot
- **Swagger UI:** http://localhost:8080/ai-springboot/swagger-ui.html
- **OpenAPI JSON:** http://localhost:8080/ai-springboot/v3/api-docs

### Test endpoints

- **GET** `/ai-springboot/api/test` – returns a greeting.
- **POST** `/ai-springboot/api/test` – send JSON body `{"message": "your text"}` and get an echo response.

Example with curl:

```bash
# GET
curl http://localhost:8080/ai-springboot/api/test

# POST
curl -X POST http://localhost:8080/ai-springboot/api/test \
  -H "Content-Type: application/json" \
  -d '{"message": "Hello from client"}'
```

You can also use Swagger UI to try GET and POST from the browser.

## Building

```bash
mvn clean package
```

The runnable JAR is produced at:

`target/ai-springboot-0.0.1-SNAPSHOT.jar`

## Deployment

1. Build the project:
   ```bash
   mvn clean package
   ```
2. Run the JAR:
   ```bash
   java -jar target/ai-springboot-0.0.1-SNAPSHOT.jar
   ```

The app listens on port 8080 and uses context path `/ai-springboot` by default.

### Overriding for different environments

- **Port:** `SERVER_PORT=9090 java -jar target/ai-springboot-0.0.1-SNAPSHOT.jar`
- **Context path:** `SERVER_SERVLET_CONTEXT_PATH=/myapp java -jar target/ai-springboot-0.0.1-SNAPSHOT.jar`

You can also set these in `application.properties` or via environment variables (e.g. `SERVER_PORT`, `SERVER_SERVLET_CONTEXT_PATH`).
