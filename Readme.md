# Item Services Application

## Overview

This application is a Spring Boot service using MongoDB as its database. The application can be containerized using Docker for seamless deployment.

---

## Dockerfile


```dockerfile
# Use a lightweight OpenJDK image
FROM openjdk:18-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the jar file into the container
COPY build/libs/item-services-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application will run on
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "app.jar"]
```

---

## Building and Running the Docker Image

### 1. Clone the Repository

```bash
git clone https://github.com/your-repo/item-services.git
cd item-services
```

### 2. Build the Application

Use Gradle to create the JAR file:

```bash
./gradlew clean build -x test
```

This command generates a JAR file in the `build/libs/` directory.

### 3. Create the `.env` File

Create a `.env` file in the project root with the following content:

```env
MONGO_URI=mongodb+srv://<username>:<password>@cluster0.mongodb.net/itemdb?retryWrites=true&w=majority
SPRING_SECURITY_USER_NAME=admin
SPRING_SECURITY_USER_PASSWORD=admin123
```

Replace `<username>` and `<password>` with your MongoDB credentials.

### 4. Build the Docker Image

Run the following command to build the Docker image:

```bash
docker build -t item-services:latest .
```

### 5. Run the Docker Container

Start a container using the image:

```bash
docker run -p 8080:8080 --env-file .env --name item-services item-services:latest
```

- `-p 8080:8080`: Maps the application port to your host's port 8080.
- `--env-file .env`: Supplies environment variables from the `.env` file.

---

## API Usage

### Base URL

```
http://localhost:8080
```

### Example Endpoints

#### 1. Create an Item

- **URL**: `/api/items`
- **Method**: `POST`
- **Headers**: `Authorization: Basic base64(admin:admin123)`
- **Request Body** (JSON):
  ```json
  {
    "name": "Sample Item",
    "description": "This is a sample description."
  }
  ```
- **Response**:
  ```json
  {
    "name": "Sample Item",
    "description": "This is a sample description."
  }
  ```

#### 2. Retrieve All Items

- **URL**: `/api/items`
- **Method**: `GET`
- **Headers**: `Authorization: Basic base64(admin:admin123)`

#### 3. Retrieve Item by ID

- **URL**: `/api/items/{id}`
- **Method**: `GET`
- **Headers**: `Authorization: Basic base64(admin:admin123)`
- **Response Example**:
  ```json
  {
    "id": "60d9f114f3d0a53b5447a00d",
    "name": "Sample Item",
    "description": "This is a sample description."
  }
  ```

---

## Stopping and Removing the Docker Container

### 1. Stop the Container:

```bash
docker stop item-services
```

### 2. Remove the Container:

```bash
docker rm item-services
```

### 3. Remove the Image (Optional):

```bash
docker rmi item-services:latest
```

---

## Troubleshooting

- **Port Conflicts**: Ensure port 8080 is not being used by another application.
- **Environment Variables**: Double-check the `.env` file for typos or missing variables.

---

---

